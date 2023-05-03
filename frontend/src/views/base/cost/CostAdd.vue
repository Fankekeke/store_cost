<template>
  <a-modal v-model="show" title="新增成本" @cancel="onClose" :width="1200">
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
              :disabled="goodsCheckLock"
              v-model="checkMaterial"
              mode="multiple"
              style="width: 100%"
              option-label-prop="label"
            >
              <a-select-option v-for="(item, index) in materialList" :value="item.materialId" :key="index" :label="item.materialName">
                {{ item.materialName }}
              </a-select-option>
            </a-select>
          </a-form-item>
          <a-button style="margin-top: 5px;margin-bottom: 10px" @click="goodsCheck">
            确认物品
          </a-button>
        </a-col>
        <a-col :span="24">
          <!-- 表格区域 -->
          <a-table bordered ref="TableInfo"
                   :rowKey="record => record.id"
                  :columns="dataColumns"
                  :dataSource="dataSource"
                  :scroll="{ x: 900 }">
            <template slot="jobName" slot-scope="text, record">
              <a-input v-model="record.jobName"/>
            </template>
            <template slot="jobCost" slot-scope="text, record">
              <a-input-number :min="1" :max="999999" v-model="record.jobCost"/>
            </template>
            <template slot="jobMotivation" slot-scope="text, record">
              <a-input v-model="record.jobMotivation"/>
            </template>
          </a-table>
          <a-button type="primary" style="margin-top: 30px" @click="dataAddChange">
            添加
          </a-button>
          <a-button type="primary" style="margin-top: 30px" @click="dataAddChange1">
            计算
          </a-button>
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
      checkMaterial: [],
      dataSource: [],
      goodsCheckLock: false,
      dataColumns: [{
        title: '作业',
        dataIndex: 'jobName',
        scopedSlots: {customRender: 'jobName'}
      }, {
        title: '作业成本',
        dataIndex: 'jobCost',
        scopedSlots: {customRender: 'jobCost'}
      }, {
        title: '作业动因',
        dataIndex: 'jobMotivation',
        scopedSlots: {customRender: 'jobMotivation'}
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
  mounted () {
    this.selectMaterialList()
  },
  methods: {
    dataAddChange () {
      if (this.checkMaterial.length === 0) {
        this.$message.error('请先选择物品！')
        return false
      }
      if (!this.goodsCheckLock) {
        this.$message.error('请确认物品')
        return false
      }
      let row = {id: Date.now(), jobName: '', jobCost: 0, jobMotivation: '', costRate: 0}
      this.materialList.forEach(e => {
        this.checkMaterial.forEach(ee => {
          if (e.materialId === ee) {
            row[ee] = e.num
          }
        })
      })
      this.dataSource.push(row)
      console.log(JSON.stringify(this.dataSource))
    },
    dataAddChange1 () {
      if (this.dataSource.length === 0) {
        this.$message.error('添加成本信息！')
        return false
      }
      // 获取合计
      let total = 0
      this.materialList.forEach(e => {
        this.checkMaterial.forEach(ee => {
          if (e.materialId === ee) {
            total = total + e.num
          }
        })
      })
      this.dataSource.forEach(e => {
        e.costRate = (e.jobCost / total).toFixed(2)
      })
      console.log(JSON.stringify(this.dataSource))
    },
    goodsCheck () {
      if (this.checkMaterial.length === 0) {
        this.$message.error('请先选择物品！')
        return false
      }
      this.goodsCheckLock = true
      // 设置表头
      let dataColumns = []
      this.materialList.forEach(e => {
        this.checkMaterial.forEach(ee => {
          if (e.materialId === ee) {
            dataColumns.push({title: e.materialName.slice(0, 8) + '...', dataIndex: ee})
          }
        })
      })
      this.dataColumns[3].children = dataColumns
    },
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
      if (this.dataSource.length === 0) {
        this.$message.error('请完整填写！')
        return false
      }
      this.form.validateFields((err, values) => {
        let materialCostNum = []
        // 获取合计
        let total = 0
        this.materialList.forEach(e => {
          this.checkMaterial.forEach(ee => {
            if (e.materialId === ee) {
              materialCostNum.push({materialId: e.materialId, materialName: e.materialName, num: e.num})
              total = total + e.num
            }
          })
        })
        let costList = []
        this.dataSource.forEach(e => {
          costList.push({jobName: e.jobName, jobCost: e.jobCost, jobMotivation: e.jobMotivation, materialCostNum: JSON.stringify(materialCostNum)})
        })
        values.costList = JSON.stringify(costList)
        if (!err) {
          this.loading = true
          this.$post('/cos/job-cost-info/cost/order', {
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
