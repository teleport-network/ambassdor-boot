<template>
  <a-table
    rowKey="id"
    size="middle"
    bordered
    :loading="loading"
    :columns="columns"
    :dataSource="dataSource"
    :pagination="false"
  >

    <template slot="htmlSlot" slot-scope="text">
      <div v-html="text"></div>
    </template>

    <template slot="imgSlot" slot-scope="text,record">
      <div style="font-size: 12px;font-style: italic;">
        <span v-if="!text">无图片</span>
        <img v-else :src="getImgView(text)" :preview="record.id" alt="" style="max-width:80px;height:25px;"/>
      </div>
    </template>

    <template slot="fileSlot" slot-scope="text">
      <span v-if="!text" style="font-size: 12px;font-style: italic;">无文件</span>
      <a-button
              v-else
              ghost
              type="primary"
              icon="download"
              size="small"
              @click="downloadFile(text)"
      >
        <span>下载</span>
      </a-button>
    </template>

  </a-table>
</template>

<script>
  import { getAction } from '@api/manage'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'

  export default {
    name: 'ActionDefSubTable',
    mixins: [JeecgListMixin],
    props: {
      record: {
        type: Object,
        default: null,
      }
    },
    data() {
      return {
        description: 'quest def内嵌列表',
        disableMixinCreated: true,
        loading: false,
        dataSource: [],
        columns: [
          {
            title: 'type',
            align: 'center',
            dataIndex: 'type',
          },
          {
            title: 'worth',
            align: 'center',
            dataIndex: 'worth',
          },
          {
            title: 'mandatory',
            align: 'center',
            dataIndex: 'mandatory',
          },
          {
            title: 'config1',
            align: 'center',
            dataIndex: 'config1',
          },
          {
            title: 'config2',
            align: 'center',
            dataIndex: 'config2',
          },
          {
            title: 'config3',
            align: 'center',
            dataIndex: 'config3',
          },
          {
            title: 'config4',
            align: 'center',
            dataIndex: 'config4',
          },
          {
            title: 'config5',
            align: 'center',
            dataIndex: 'config5',
          },
          {
            title: 'config6',
            align: 'center',
            dataIndex: 'config6',
          },
          {
            title: 'config7',
            align: 'center',
            dataIndex: 'config7',
          },
          {
            title: 'config8',
            align: 'center',
            dataIndex: 'config8',
          },
          {
            title: 'config9',
            align: 'center',
            dataIndex: 'config9',
          },
          {
            title: 'original',
            align: 'center',
            dataIndex: 'original',
          },
          {
            title: 'image',
            align: 'center',
            dataIndex: 'image',
          },
          {
            title: 'paid',
            align: 'center',
            dataIndex: 'paid',
          },
          {
            title: 'config_toggle',
            align: 'center',
            dataIndex: 'configToggle',
          },
          {
            title: 'actions_required',
            align: 'center',
            dataIndex: 'actionsRequired',
          },
        ],
        url: {
          listByMainId: '/amquest/quest/queryActionDefByMainId',
        },
      }
    },
    watch: {
      record: {
        immediate: true,
        handler() {
          if (this.record != null) {
            this.loadData(this.record)
          }
        }
      }
    },
    methods: {

      loadData(record) {
        this.loading = true
        this.dataSource = []
        getAction(this.url.listByMainId, {
          id: record.id
        }).then((res) => {
          if (res.success) {
            this.dataSource = res.result.records
          }
        }).finally(() => {
          this.loading = false
        })
      },

    },
  }
</script>

<style scoped>

</style>
