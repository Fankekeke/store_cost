<template>
  <a-modal v-model="show" title="成本详情" @cancel="onClose" :width="1200">
    <template slot="footer">
      <a-button key="back" @click="onClose" type="danger">
        关闭
      </a-button>
    </template>
    <div style="font-size: 13px;font-family: SimHei" v-if="recordData !== null">
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col style="margin-bottom: 15px"><span style="font-size: 15px;font-weight: 650;color: #000c17">基础信息</span></a-col>
        <a-col :span="8"><b>成本名称：</b>
          {{ recordData.orderName !== null ? recordData.orderName : '- -' }}
        </a-col>
        <a-col :span="8"><b>成本编号：</b>
          {{ recordData.orderCode }}
        </a-col>
        <a-col :span="8"><b>创建人：</b>
          {{ recordData.createBy }}
        </a-col>
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col :span="8"><b>创建时间：</b>
          {{ recordData.createDate !== null ? recordData.createDate : '- -' }}
        </a-col>
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col style="margin-bottom: 15px"><span style="font-size: 15px;font-weight: 650;color: #000c17">备 注</span></a-col>
        {{ recordData.remark }}
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;" :gutter="15">
        <a-col style="margin-bottom: 15px"><span style="font-size: 15px;font-weight: 650;color: #000c17">作业成品分配</span></a-col>
        <a-col :span="24">
          <a-table bordered :columns="dataColumns" :data-source="dataSource">
          </a-table>
        </a-col>
      </a-row>
    </div>
  </a-modal>
</template>

<script>
import moment from 'moment'
moment.locale('zh-cn')
export default {
  name: 'RecordView',
  props: {
    recordShow: {
      type: Boolean,
      default: false
    },
    recordData: {
      type: Object
    }
  },
  computed: {
    show: {
      get: function () {
        return this.recordShow
      },
      set: function () {
      }
    }
  },
  data () {
    return {
      loading: false,
      goodsList: [],
      dataColumns: [],
      dataSource: []
    }
  },
  watch: {
    recordShow: function (value) {
      if (value) {
        this.getGoodsByNum(this.recordData.id)
      }
    }
  },
  methods: {
    getGoodsByNum (num) {
      this.$get(`/cos/job-cost-info/header/${num}`).then((r) => {
        this.dataColumns = r.data.header
        this.dataSource = r.data.data
      })
    },
    onClose () {
      this.$emit('close')
    }
  }
}
</script>

<style scoped>
  /deep/ .ant-table-tbody {
    font-size: 12px;
  }
  /deep/ .ant-table-thead {
    font-size: 13px;
  }
</style>
