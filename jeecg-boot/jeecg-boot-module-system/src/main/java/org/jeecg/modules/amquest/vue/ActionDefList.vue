<template>
  <a-card :bordered="false" :class="'cust-erp-sub-tab'">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
        </a-row>
      </a-form>
    </div>
    <!-- 查询区域-END -->

    <!-- 操作按钮区域 -->
    <div class="table-operator" v-if="mainId">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('quest def')">导出</a-button>
      <a-upload
        name="file"
        :showUploadList="false"
        :multiple="false"
        :headers="tokenHeader"
        :action="importExcelUrl"
        @change="handleImportExcel">
          <a-button type="primary" icon="import">导入</a-button>
      </a-upload>
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
      </a-dropdown>
    </div>

    <!-- table区域-begin -->
    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div>

      <a-table
        ref="table"
        size="middle"
        bordered
        rowKey="id"
        :scroll="{x:true}"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        @change="handleTableChange">

        <template slot="htmlSlot" slot-scope="text">
          <div v-html="text"></div>
        </template>
        <template slot="imgSlot" slot-scope="text,record">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无图片</span>
          <img v-else :src="getImgView(text)" :preview="record.id" height="25px" alt="" style="max-width:80px;font-size: 12px;font-style: italic;"/>
        </template>
        <template slot="fileSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无文件</span>
          <a-button
            v-else
            :ghost="true"
            type="primary"
            icon="download"
            size="small"
            @click="downloadFile(text)">
            下载
          </a-button>
        </template>

        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>
          <a-divider type="vertical" />
          <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
            <a>删除</a>
          </a-popconfirm>
        </span>

      </a-table>
    </div>

    <actionDef-modal ref="modalForm" @ok="modalFormOk" :mainId="mainId"></actionDef-modal>
  </a-card>
</template>

<script>

  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import ActionDefModal from './modules/ActionDefModal'

  export default {
    name: "ActionDefList",
    mixins:[JeecgListMixin],
    components: { ActionDefModal },
    props:{
      mainId:{
        type:String,
        default:'',
        required:false
      }
    },
    watch:{
      mainId:{
        immediate: true,
        handler(val) {
          if(!this.mainId){
            this.clearList()
          }else{
            this.queryParam['questFk'] = val
            this.loadData(1);
          }
        }
      }
    },
    data () {
      return {
        description: 'quest管理页面',
        disableMixinCreated:true,
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key:'rowIndex',
            width:60,
            align:"center",
            customRender:function (t,r,index) {
              return parseInt(index)+1;
            }
          },
          {
            title:'type',
            align:"center",
            dataIndex: 'type'
          },
          {
            title:'Categories',
            align:"center",
            dataIndex: 'categories_dictText',
          },
          {
            title:'points rewards',
            align:"center",
            dataIndex: 'reward'
          },
          {
            title:'worth',
            align:"center",
            dataIndex: 'worth'
          },
          {
            title:'mandatory',
            align:"center",
            dataIndex: 'mandatory'
          },
          {
            title:'config1',
            align:"center",
            dataIndex: 'config1'
          },
          {
            title:'config2',
            align:"center",
            dataIndex: 'config2'
          },
          {
            title:'config3',
            align:"center",
            dataIndex: 'config3'
          },
          {
            title:'config4',
            align:"center",
            dataIndex: 'config4'
          },
          {
            title:'config5',
            align:"center",
            dataIndex: 'config5'
          },
          {
            title:'config6',
            align:"center",
            dataIndex: 'config6'
          },
          {
            title:'config7',
            align:"center",
            dataIndex: 'config7'
          },
          {
            title:'config8',
            align:"center",
            dataIndex: 'config8'
          },
          {
            title:'config9',
            align:"center",
            dataIndex: 'config9'
          },
          {
            title:'original',
            align:"center",
            dataIndex: 'original'
          },
          {
            title:'image',
            align:"center",
            dataIndex: 'image'
          },
          {
            title:'paid',
            align:"center",
            dataIndex: 'paid'
          },
          {
            title:'config_toggle',
            align:"center",
            dataIndex: 'configToggle'
          },
          {
            title:'actions_required',
            align:"center",
            dataIndex: 'actionsRequired'
          },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            fixed:"right",
            width:147,
            scopedSlots: { customRender: 'action' },
          }
        ],
        url: {
          list: "/amquest/quest/listActionDefByMainId",
          delete: "/amquest/quest/deleteActionDef",
          deleteBatch: "/amquest/quest/deleteBatchActionDef",
          exportXlsUrl: "/amquest/quest/exportActionDef",
          importUrl: "/amquest/quest/importActionDef",
        },
        dictOptions:{
         type:[],
         assignTo:[],
        }
      }
    },
    created() {
    },
    computed: {
      importExcelUrl(){
        return `${window._CONFIG['domianURL']}/${this.url.importUrl}/${this.mainId}`;
      }
    },
    methods: {
      clearList(){
        this.dataSource=[]
        this.selectedRowKeys=[]
        this.ipagination.current = 1
      }

    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less'
</style>
