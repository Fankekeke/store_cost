<template>
  <a-modal v-model="show" title="新增成本" @cancel="onClose" :width="800">
    <template slot="footer">
      <a-button key="back" @click="onClose">
        取消
      </a-button>
      <a-button key="submit" type="primary" :loading="loading" @click="handleSubmit">
        提交
      </a-button>
    </template>
    <a-form :form="form" layout="vertical">
      <a-row :gutter="20">
        <a-col :span="12">
          <a-form-item label='成本名称' v-bind="formItemLayout">
            <a-input v-decorator="[
            'orderName',
            { rules: [{ required: true, message: '请输入名称!' }] }
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='上传人' v-bind="formItemLayout">
            <a-input v-decorator="[
            'createBy',
            { rules: [{ required: true, message: '请输入上传人!' }] }
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label='备注' v-bind="formItemLayout">
            <a-textarea :rows="4" v-decorator="[
            'remark',
             { rules: [{ required: true, message: '请输入备注!' }] }
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label='选择物品' v-bind="formItemLayout">
            <a-select
              style="width: 100%"
              mode="tags"
              placeholder="Please select"
              :default-value="['a1', 'b2']"
              @change="handleChange"
            >
              <a-select-option v-for="i in 25" :key="(i + 9).toString(36) + i">
                {{ (i + 9).toString(36) + i }}
              </a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <!-- 表格区域 -->
          <a-table ref="TableInfo"
                  :columns="columns"
                  :dataSource="dataSource"
                  :scroll="{ x: 900 }">
            <template slot="titleShow" slot-scope="text, record">
              <template>
                <a-badge status="processing"/>
                <a-tooltip>
                  <template slot="title">
                    {{ record.title }}
                  </template>
                  {{ record.title.slice(0, 8) }} ...
                </a-tooltip>
              </template>
            </template>
            <template slot="contentShow" slot-scope="text, record">
              <template>
                <a-tooltip>
                  <template slot="title">
                    {{ record.content }}
                  </template>
                  {{ record.content.slice(0, 30) }} ...
                </a-tooltip>
              </template>
            </template>
            <template slot="operation" slot-scope="text, record">
              <a-icon type="setting" theme="twoTone" twoToneColor="#4a9ff5" @click="edit(record)" title="修 改"></a-icon>
            </template>
          </a-table>
        </a-col>
      </a-row>
    </a-form>
  </a-modal>
</template>

<script>
import {mapState} from 'vuex'
function getBase64 (file) {
  return new Promise((resolve, reject) => {
    const reader = new FileReader()
    reader.readAsDataURL(file)
    reader.onload = () => resolve(reader.result)
    reader.onerror = error => reject(error)
  })
}
const formItemLayout = {
  labelCol: { span: 24 },
  wrapperCol: { span: 24 }
}
export default {
  name: 'costAdd',
  props: {
    costAddVisiable: {
      default: false
    }
  },
  computed: {
    ...mapState({
      currentUser: state => state.account.user
    }),
    show: {
      get: function () {
        return this.costAddVisiable
      },
      set: function () {
      }
    },
    columns () {
      return [{
        title: '作业',
        dataIndex: 'jobName'
      }, {
        title: '作业成本',
        dataIndex: 'jobCost'
      }, {
        title: '作业动因',
        dataIndex: 'jobMotivation'
      }, {
        title: '作业动因数',
        children: [

        ]
      }, {
        title: '成本动因率',
        dataIndex: 'costRate'
      }]
    }
  },
  data () {
    return {
      formItemLayout,
      form: this.$form.createForm(this),
      loading: false,
      fileList: [],
      previewVisible: false,
      previewImage: '',
      materialList: [],
      dataSource: []
    }
  },
  methods: {
    selectMaterialList () {
      this.$get('/cos/storehouse-info/all/material').then((r) => {
        this.materialList = r.data.data
      })
    },
    handleCancel () {
      this.previewVisible = false
    },
    async handlePreview (file) {
      if (!file.url && !file.preview) {
        file.preview = await getBase64(file.originFileObj)
      }
      this.previewImage = file.url || file.preview
      this.previewVisible = true
    },
    picHandleChange ({ fileList }) {
      this.fileList = fileList
    },
    reset () {
      this.loading = false
      this.form.resetFields()
    },
    onClose () {
      this.reset()
      this.$emit('close')
    },
    handleSubmit () {
      this.form.validateFields((err, values) => {
        if (!err) {
          values.publisher = this.currentUser.userId
          this.loading = true
          this.$post('/cos/cost-info', {
            ...values
          }).then((r) => {
            this.reset()
            this.$emit('success')
          }).catch(() => {
            this.loading = false
          })
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
