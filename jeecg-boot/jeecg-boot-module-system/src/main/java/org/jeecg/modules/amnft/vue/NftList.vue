<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
        </a-row>
      </a-form>
    </div>
    <!-- 查询区域-END -->
    
    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('nft')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>
      <!-- 高级查询区域 -->
      <j-super-query :fieldList="superFieldList" ref="superQueryModal" @handleSuperQuery="handleSuperQuery"></j-super-query>
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
        class="j-table-force-nowrap"
        :scroll="{x:true}"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange, type:'radio'}"
        :customRow="clickThenSelect"
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
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a>删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>

      </a-table>
    </div>

    <a-tabs defaultActiveKey="1">
      <a-tab-pane tab="mint history" key="1" >
        <MintHistoryList :mainId="mintHistoryMainId" />
      </a-tab-pane>
      <a-tab-pane tab="delivered history" key="2" forceRender>
        <DeliveredHistoryList :mainId="deliveredHistoryMainId" />
      </a-tab-pane>
    </a-tabs>

    <nft-modal ref="modalForm" @ok="modalFormOk"></nft-modal>
  </a-card>
</template>

<script>

  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import NftModal from './modules/NftModal'
  import { getAction } from '@/api/manage'
  import MintHistoryList from './MintHistoryList'
  import DeliveredHistoryList from './DeliveredHistoryList'
  import '@/assets/less/TableExpand.less'

  export default {
    name: "NftList",
    mixins:[JeecgListMixin],
    components: {
      MintHistoryList,
      DeliveredHistoryList,
      NftModal
    },
    data () {
      return {
        description: 'nft管理页面',
        // 表头
        columns: [
          {
            title:'image',
            align:"center",
            dataIndex: 'image'
          },
          {
            title:'name',
            align:"center",
            dataIndex: 'name'
          },
          {
            title:'type',
            align:"center",
            dataIndex: 'type'
          },
          {
            title:'desc',
            align:"center",
            dataIndex: 'description'
          },
          {
            title:'address',
            align:"center",
            dataIndex: 'address'
          },
          {
            title:'total',
            align:"center",
            dataIndex: 'total'
          },
          {
            title:'inventory',
            align:"center",
            dataIndex: 'inventory'
          },
          {
            title:'delivered quantity',
            align:"center",
            dataIndex: 'delivered'
          },
          {
            title:'reuest quantity',
            align:"center",
            dataIndex: 'txRequestNum'
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
          list: "/amnft/nft/list",
          delete: "/amnft/nft/delete",
          deleteBatch: "/amnft/nft/deleteBatch",
          exportXlsUrl: "/amnft/nft/exportXls",
          importExcelUrl: "amnft/nft/importExcel",
        },
        dictOptions:{
        },
        /* 分页参数 */
        ipagination:{
          current: 1,
          pageSize: 5,
          pageSizeOptions: ['5', '10', '50'],
          showTotal: (total, range) => {
            return range[0] + "-" + range[1] + " 共" + total + "条"
          },
          showQuickJumper: true,
          showSizeChanger: true,
          total: 0
        },
        selectedMainId:'',
        superFieldList:[],
        mintHistoryMainId: '',
        deliveredHistoryMainId: '',
      }
    },
    created() {
      this.getSuperFieldList();
    },
    computed: {
      importExcelUrl: function(){
        return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
      }
    },
    methods: {
      initDictConfig(){
      },
      clickThenSelect(record) {
        return {
          on: {
            click: () => {
              this.onSelectChange(record.id.split(","), [record]);
            }
          }
        }
      },
      onClearSelected() {
        this.selectedRowKeys = [];
        this.selectionRows = [];
        this.selectedMainId=''
      },
      onSelectChange(selectedRowKeys, selectionRows) {
        this.selectedMainId=selectedRowKeys[0]
        this.selectedRowKeys = selectedRowKeys;
        this.selectionRows = selectionRows;
        this.mintHistoryMainId = selectionRows[0]['id']
        this.deliveredHistoryMainId = selectionRows[0]['id']
      },
      loadData(arg) {
        if(!this.url.list){
          this.$message.error("请设置url.list属性!")
          return
        }
        //加载数据 若传入参数1则加载第一页的内容
        if (arg === 1) {
          this.ipagination.current = 1;
        }
        this.onClearSelected()
        var params = this.getQueryParams();//查询条件
        this.loading = true;
        getAction(this.url.list, params).then((res) => {
          if (res.success) {
            this.dataSource = res.result.records;
            this.ipagination.total = res.result.total;
          }
          if(res.code===510){
            this.$message.warning(res.message)
          }
          this.loading = false;
        })
      },
      getSuperFieldList(){
        let fieldList=[];
        fieldList.push({type:'string',value:'image',text:'image',dictCode:''})
        fieldList.push({type:'string',value:'name',text:'name',dictCode:''})
        fieldList.push({type:'string',value:'type',text:'type',dictCode:''})
        fieldList.push({type:'Text',value:'description',text:'desc',dictCode:''})
        fieldList.push({type:'string',value:'address',text:'address',dictCode:''})
        fieldList.push({type:'int',value:'total',text:'total',dictCode:''})
        fieldList.push({type:'int',value:'inventory',text:'inventory',dictCode:''})
        fieldList.push({type:'int',value:'delivered',text:'delivered quantity',dictCode:''})
        fieldList.push({type:'int',value:'txRequestNum',text:'reuest quantity',dictCode:''})
        this.superFieldList = fieldList
      }
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less'
</style>