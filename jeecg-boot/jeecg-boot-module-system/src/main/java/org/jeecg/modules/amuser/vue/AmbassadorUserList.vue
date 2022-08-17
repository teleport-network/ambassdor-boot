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
      <a-button type="primary" icon="download" @click="handleExportXls('ambassador user list')">导出</a-button>
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
      <a-tab-pane tab="points list" key="1" >
        <AmUserPointList :mainId="amUserPointMainId" />
      </a-tab-pane>
      <a-tab-pane tab="token list" key="2" forceRender>
        <AmUserTokenList :mainId="amUserTokenMainId" />
      </a-tab-pane>
      <a-tab-pane tab="nft list" key="3" forceRender>
        <AmUserNftList :mainId="amUserNftMainId" />
      </a-tab-pane>
      <a-tab-pane tab="quest list" key="4" forceRender>
        <AmUserQuestList :mainId="amUserQuestMainId" />
      </a-tab-pane>
    </a-tabs>

    <ambassadorUser-modal ref="modalForm" @ok="modalFormOk"></ambassadorUser-modal>
  </a-card>
</template>

<script>

  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import AmbassadorUserModal from './modules/AmbassadorUserModal'
  import { getAction } from '@/api/manage'
  import AmUserPointList from './AmUserPointList'
  import AmUserTokenList from './AmUserTokenList'
  import AmUserNftList from './AmUserNftList'
  import AmUserQuestList from './AmUserQuestList'
  import {initDictOptions,filterMultiDictText} from '@/components/dict/JDictSelectUtil'
  import '@/assets/less/TableExpand.less'

  export default {
    name: "AmbassadorUserList",
    mixins:[JeecgListMixin],
    components: {
      AmUserPointList,
      AmUserTokenList,
      AmUserNftList,
      AmUserQuestList,
      AmbassadorUserModal
    },
    data () {
      return {
        description: 'ambassador user list管理页面',
        // 表头
        columns: [
          {
            title:'evm address',
            align:"center",
            dataIndex: 'address'
          },
          {
            title:'邮箱',
            align:"center",
            dataIndex: 'email'
          },
          {
            title:'用户名',
            align:"center",
            dataIndex: 'username'
          },
          {
            title:'头像',
            align:"center",
            dataIndex: 'avatar',
            scopedSlots: {customRender: 'imgSlot'}
          },
          {
            title:'角色',
            align:"center",
            dataIndex: 'role_dictText',
          },
          {
            title:'国家/地区',
            align:"center",
            dataIndex: 'location'
          },
          {
            title:'当前token数量',
            align:"center",
            dataIndex: 'token'
          },
          {
            title:'当前points数量',
            align:"center",
            dataIndex: 'point'
          },
          {
            title:'当前未收集的points',
            align:"center",
            dataIndex: 'pointCache'
          },
          {
            title:'当前等级',
            align:"center",
            dataIndex: 'level_dictText',
          },
          {
            title:'discord id',
            align:"center",
            dataIndex: 'discordId'
          },
          {
            title:'twitter id',
            align:"center",
            dataIndex: 'twitterId'
          },
          {
            title:'telegram id',
            align:"center",
            dataIndex: 'telegramId'
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
          list: "/amuser/ambassadorUser/list",
          delete: "/amuser/ambassadorUser/delete",
          deleteBatch: "/amuser/ambassadorUser/deleteBatch",
          exportXlsUrl: "/amuser/ambassadorUser/exportXls",
          importExcelUrl: "amuser/ambassadorUser/importExcel",
        },
        dictOptions:{
         role:[],
         level:[],
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
        amUserPointMainId: '',
        amUserTokenMainId: '',
        amUserNftMainId: '',
        amUserQuestMainId: '',
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
        this.amUserPointMainId = selectionRows[0]['id']
        this.amUserTokenMainId = selectionRows[0]['id']
        this.amUserNftMainId = selectionRows[0]['id']
        this.amUserQuestMainId = selectionRows[0]['id']
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
        fieldList.push({type:'string',value:'address',text:'evm address',dictCode:''})
        fieldList.push({type:'string',value:'email',text:'邮箱',dictCode:''})
        fieldList.push({type:'string',value:'username',text:'用户名',dictCode:''})
        fieldList.push({type:'string',value:'avatar',text:'头像',dictCode:''})
        fieldList.push({type:'string',value:'role',text:'角色',dictCode:'user_role'})
        fieldList.push({type:'string',value:'location',text:'国家/地区',dictCode:''})
        fieldList.push({type:'BigDecimal',value:'token',text:'当前token数量',dictCode:''})
        fieldList.push({type:'BigDecimal',value:'point',text:'当前points数量',dictCode:''})
        fieldList.push({type:'BigDecimal',value:'pointCache',text:'当前未收集的points',dictCode:''})
        fieldList.push({type:'string',value:'level',text:'当前等级',dictCode:'user_level'})
        fieldList.push({type:'string',value:'discordId',text:'discord id',dictCode:''})
        fieldList.push({type:'string',value:'twitterId',text:'twitter id',dictCode:''})
        fieldList.push({type:'string',value:'telegramId',text:'telegram id',dictCode:''})
        this.superFieldList = fieldList
      }
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less'
</style>