<template>
  <a-card class="j-inner-table-wrapper" :bordered="false">

    <!-- 查询区域 begin -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="24">
        </a-row>
      </a-form>
    </div>
    <!-- 查询区域 end -->

    <!-- 操作按钮区域 begin -->
    <div class="table-operator">
      <a-button type="primary" icon="plus" @click="handleAdd">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('quest')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>
      <!-- 高级查询区域 -->
      <j-super-query :fieldList="superFieldList" ref="superQueryModal" @handleSuperQuery="handleSuperQuery"></j-super-query>
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel">
            <a-icon type="delete"/>
            <span>删除</span>
          </a-menu-item>
        </a-menu>
        <a-button>
          <span>批量操作</span>
          <a-icon type="down"/>
        </a-button>
      </a-dropdown>
    </div>
    <!-- 操作按钮区域 end -->

    <!-- table区域 begin -->
    <div>

      <a-alert type="info" showIcon style="margin-bottom: 16px;">
        <template slot="message">
          <span>已选择</span>
          <a style="font-weight: 600;padding: 0 4px;">{{ selectedRowKeys.length }}</a>
          <span>项</span>
          <a style="margin-left: 24px" @click="onClearSelected">清空</a>
        </template>
      </a-alert>

      <a-table
        ref="table"
        size="middle"
        bordered
        rowKey="id"
        class="j-table-force-nowrap"
        :scroll="{x:true}"
        :loading="loading"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :expandedRowKeys="expandedRowKeys"
        :rowSelection="{selectedRowKeys, onChange: onSelectChange}"
        @expand="handleExpand"
        @change="handleTableChange"
      >

        <!-- 内嵌table区域 begin -->
        <template slot="expandedRowRender" slot-scope="record">
          <a-tabs tabPosition="top">
            <a-tab-pane tab="quest def" key="actionDef" forceRender>
              <action-def-sub-table :record="record"/>
            </a-tab-pane>
          </a-tabs>
        </template>
        <!-- 内嵌table区域 end -->

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

        <template slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>
          <a-divider type="vertical"/>
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="handleDelete(record.id)">
                  <a>删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>

        </template>

      </a-table>
    </div>
    <!-- table区域 end -->

    <!-- 表单区域 -->
    <quest-modal ref="modalForm" @ok="modalFormOk"/>

  </a-card>
</template>

<script>

  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import QuestModal from './modules/QuestModal'
  import ActionDefSubTable from './subTables/ActionDefSubTable'
  import '@/assets/less/TableExpand.less'

  export default {
    name: 'QuestList',
    mixins: [JeecgListMixin],
    components: {
      QuestModal,
      ActionDefSubTable,
    },
    data() {
      return {
        description: 'quest列表管理页面',
        // 表头
        columns: [
          {
            title: '#',
            key: 'rowIndex',
            width: 60,
            align: 'center',
            customRender: (t, r, index) => parseInt(index) + 1
          },
          {
            title: 'quest key',
            align: 'center',
            dataIndex: 'questKey',
          },
          {
            title: 'title',
            align: 'center',
            dataIndex: 'title',
          },
          {
            title: 'quest link',
            align: 'center',
            dataIndex: 'url',
          },
          {
            title: 'rewards points',
            align: 'center',
            dataIndex: 'rewards',
          },
          {
            title: 'type',
            align: 'center',
            dataIndex: 'type',
          },
          {
            title: 'image',
            align: 'center',
            dataIndex: 'image',
            scopedSlots: {customRender: 'imgSlot'}
          },
          {
            title: 'quest date',
            align: 'center',
            dataIndex: 'issueDate',
          },
          {
            title: 'queat duration',
            align: 'center',
            dataIndex: 'deadline',
          },
          {
            title: 'last sync time',
            align: 'center',
            dataIndex: 'syncTime',
          },
          {
            title: 'create time in gleam',
            align: 'center',
            dataIndex: 'gleamCreateAt',
          },
          {
            title: 'fraud_type',
            align: 'center',
            dataIndex: 'fraudType',
          },
          {
            title: '操作',
            dataIndex: 'action',
            align: 'center',
            width:147,
            scopedSlots: { customRender: 'action' },
          },
        ],
        // 字典选项
        dictOptions: {},
        // 展开的行test
        expandedRowKeys: [],
        url: {
          list: '/amquest/quest/list',
          delete: '/amquest/quest/delete',
          deleteBatch: '/amquest/quest/deleteBatch',
          exportXlsUrl: '/amquest/quest/exportXls',
          importExcelUrl: '/amquest/quest/importExcel',
        },
        superFieldList:[],
      }
    },
    created() {
      this.getSuperFieldList();
    },
    computed: {
      importExcelUrl() {
        return window._CONFIG['domianURL'] + this.url.importExcelUrl
      }
    },
    methods: {
      initDictConfig() {
      },

      handleExpand(expanded, record) {
        this.expandedRowKeys = []
        if (expanded === true) {
          this.expandedRowKeys.push(record.id)
        }
      },
      getSuperFieldList(){
        let fieldList=[];
        fieldList.push({type:'string',value:'questKey',text:'quest key',dictCode:''})
        fieldList.push({type:'string',value:'title',text:'title',dictCode:''})
        fieldList.push({type:'string',value:'url',text:'quest link',dictCode:''})
        fieldList.push({type:'int',value:'rewards',text:'rewards points',dictCode:''})
        fieldList.push({type:'string',value:'type',text:'type',dictCode:''})
        fieldList.push({type:'string',value:'image',text:'image',dictCode:''})
        fieldList.push({type:'datetime',value:'issueDate',text:'quest date'})
        fieldList.push({type:'datetime',value:'deadline',text:'queat duration'})
        fieldList.push({type:'datetime',value:'syncTime',text:'last sync time'})
        fieldList.push({type:'datetime',value:'gleamCreateAt',text:'create time in gleam'})
        fieldList.push({type:'string',value:'fraudType',text:'fraud_type',dictCode:''})
        this.superFieldList = fieldList
      }
    }
  }
</script>
<style lang="less" scoped>
  @import '~@assets/less/common.less';
</style>