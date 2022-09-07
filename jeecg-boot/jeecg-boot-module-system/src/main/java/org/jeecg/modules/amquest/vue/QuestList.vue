<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="type">
              <j-dict-select-tag placeholder="请选择type" v-model="queryParam.type" dictCode="quest_type"/>
            </a-form-item>
          </a-col>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="assign to">
              <j-dict-select-tag placeholder="请选择assign to" v-model="queryParam.assignTo" dictCode="user_role"/>
            </a-form-item>
          </a-col>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
              <a @click="handleToggleSearch" style="margin-left: 8px">
                {{ toggleSearchStatus ? '收起' : '展开' }}
                <a-icon :type="toggleSearchStatus ? 'up' : 'down'"/>
              </a>
            </span>
          </a-col>
        </a-row>
      </a-form>
    </div>
    <!-- 查询区域-END -->
    
    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('quest')">导出</a-button>
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
      <a-tab-pane tab="quest def" key="1" >
        <ActionDefList :mainId="actionDefMainId" />
      </a-tab-pane>
    </a-tabs>

    <quest-modal ref="modalForm" @ok="modalFormOk"></quest-modal>
  </a-card>
</template>

<script>

  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import QuestModal from './modules/QuestModal'
  import { getAction } from '@/api/manage'
  import ActionDefList from './ActionDefList'
  import {initDictOptions,filterMultiDictText} from '@/components/dict/JDictSelectUtil'
  import '@/assets/less/TableExpand.less'

  export default {
    name: "QuestList",
    mixins:[JeecgListMixin],
    components: {
      ActionDefList,
      QuestModal
    },
    data () {
      return {
        description: 'quest管理页面',
        // 表头
        columns: [
          {
            title:'quest key',
            align:"center",
            dataIndex: 'questKey'
          },
          {
            title:'active',
            align:"center",
            dataIndex: 'active',
            customRender: (text) => (!text ? "" : (text == "Y" ? "是" : "否"))
          },
          {
            title:'title',
            align:"center",
            dataIndex: 'title'
          },
          {
            title:'quest link',
            align:"center",
            dataIndex: 'url'
          },
          {
            title:'rewards points',
            align:"center",
            dataIndex: 'rewards'
          },
          {
            title:'type',
            align:"center",
            sorter: true,
            dataIndex: 'type_dictText',
          },
          {
            title:'assign to',
            align:"center",
            sorter: true,
            dataIndex: 'assignTo_dictText',
          },
          {
            title:'description',
            align:"center",
            dataIndex: 'description',
            scopedSlots: {customRender: 'htmlSlot'}
          },
          {
            title:'image',
            align:"center",
            dataIndex: 'image',
            scopedSlots: {customRender: 'imgSlot'}
          },
          {
            title:'quest date',
            align:"center",
            sorter: true,
            dataIndex: 'issueDate'
          },
          {
            title:'queat duration',
            align:"center",
            sorter: true,
            dataIndex: 'deadline'
          },
          {
            title:'last sync time',
            align:"center",
            sorter: true,
            dataIndex: 'syncTime'
          },
          {
            title:'create time in gleam',
            align:"center",
            sorter: true,
            dataIndex: 'gleamCreateAt'
          },
          {
            title:'fraud_type',
            align:"center",
            dataIndex: 'fraudType'
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
          list: "/amquest/quest/list",
          delete: "/amquest/quest/delete",
          deleteBatch: "/amquest/quest/deleteBatch",
          exportXlsUrl: "/amquest/quest/exportXls",
          importExcelUrl: "amquest/quest/importExcel",
        },
        dictOptions:{
         type:[],
         assignTo:[],
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
        actionDefMainId: '',
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
        this.actionDefMainId = selectionRows[0]['id']
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
        fieldList.push({type:'string',value:'questKey',text:'quest key',dictCode:''})
        fieldList.push({type:'switch',value:'active',text:'active'})
        fieldList.push({type:'string',value:'title',text:'title',dictCode:''})
        fieldList.push({type:'string',value:'url',text:'quest link',dictCode:''})
        fieldList.push({type:'int',value:'rewards',text:'rewards points',dictCode:''})
        fieldList.push({type:'string',value:'type',text:'type',dictCode:'quest_type'})
        fieldList.push({type:'string',value:'assignTo',text:'assign to',dictCode:'user_role'})
        fieldList.push({type:'Text',value:'description',text:'description',dictCode:''})
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
<style scoped>
  @import '~@assets/less/common.less'
</style>