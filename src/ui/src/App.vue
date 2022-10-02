<template>
  <h1>{{ msg }}</h1>
  <TabView
    v-on:tab-change="
      initApp();
      initCurrentNurse();
      initCurrentStation();
    "
  >
    <TabPanel header="站點列表" key="tab_station">
      <div class="flex justify-content-center">
        <div class="col-12 md:col-6">
          <div class="p-inputgroup">
            <InputText v-show="current_station.stationId >= 0" type="text" placeholder="請輸入站點名稱" value="" @click="initCurrentStation" />
            <InputText v-show="current_station.stationId == -1" placeholder="請輸入站點名稱" type="text" v-model="current_station.stationName" />
            <Button icon="pi pi-plus" label="新增" class="p-button-success" @click="addStation(current_station)" />
          </div>
        </div>
      </div>
      <h2>站點列表</h2>
      <DataTable :value="stations_with_nurses" dataKey="stationId" :paginator="true" :rows="10" responsiveLayout="scroll">
        <Column field="stationName" header="站點名稱" :sortable="true" style="min-width: 16rem"></Column>
        <Column field="updatedDate" header="修改時間" :sortable="true" style="min-width: 16rem"></Column>
        <Column field="nurse_in_station" header="站點護士列表" :sortable="true" style="min-width: 12rem">
          <template #body="slotProps">
            <Button v-for="i in slotProps.data[slotProps.field]" :key="i.nurseId" :label="i.employeeId" class="p-button-raised p-button-text p-button-plain mr-2" disabled="disabled" />
          </template>
        </Column>
        <Column :exportable="false" style="min-width: 8rem">
          <template #body="slotProps">
            <Button icon="pi pi-pencil" class="p-button-rounded p-button-success mr-2" @click="editStationDialog(slotProps.data)" />
            <Button icon="pi pi-trash" class="p-button-rounded p-button-warning" @click="confirmDelete(slotProps.data)" />
          </template>
        </Column>
      </DataTable>
    </TabPanel>
    <TabPanel header="新增護士" key="tab_new_nurse">
      <div class="flex-column justify-content-center">
        <div class="align-items-center">
          <div class="mb-2">
            <label for="username2">員工編號：</label>
            <InputText v-show="current_nurse.nurseId >= 0" type="text" placeholder="請輸入員工編號" value="" @click="initCurrentNurse" />
            <InputText v-show="current_nurse.nurseId == -1" placeholder="請輸入員工編號" type="text" v-model="current_nurse.employeeId" />
          </div>
          <div class="mb-2">
            <label for="username2">護士姓名：</label>
            <InputText v-show="current_nurse.nurseId >= 0" placeholder="請輸入護士名稱" type="text" value="" @click="initCurrentNurse" />
            <InputText v-show="current_nurse.nurseId == -1" placeholder="請輸入護士名稱" type="text" v-model="current_nurse.nurseName" />
          </div>
          <h3>選取站點</h3>
          <div class="align-items-center mb-4">
            <PickList v-model="picklist_stations" listStyle="height:35vh" dataKey="stationId">
              <template #sourceheader> 全部站點 </template>
              <template #targetheader> 已選站點 </template>
              <template #item="slotProps">
                <div class="product-item border-bottom-2">
                  <div class="product-list-detail">
                    <h3>{{ slotProps.item.stationName }}</h3>
                  </div>
                  <!-- <div class="product-list-detail">
                    <h5 v-if="slotProps.item.nurse_in_station && slotProps.item.nurse_in_station != []">(目前人數：{{ slotProps.item.nurse_in_station.length }})</h5>
                  </div> -->
                </div>
              </template>
            </PickList>
          </div>
          <Button icon="pi pi-plus" label="新增" class="p-button-lg p-button-success block w-full" @click="addNurse" />
        </div>
      </div>
    </TabPanel>
    <TabPanel header="護士列表" key="tab_nurse">
      <h2>護士列表</h2>
      <DataTable :value="nurses" dataKey="nurseId" :paginator="true" :rows="10" responsiveLayout="scroll">
        <Column field="nurseId" header="員工編號" style="min-width: 16rem"></Column>
        <Column field="nurseName" header="姓名" style="min-width: 16rem"></Column>
        <Column field="updatedDate" header="修改時間" style="min-width: 16rem"></Column>
        <Column :exportable="false" style="min-width: 8rem">
          <template #body="slotProps">
            <Button icon="pi pi-pencil" class="p-button-rounded p-button-success mr-2" @click="editNurse(slotProps.data)" />
            <Button icon="pi pi-trash" class="p-button-rounded p-button-warning" @click="confirmDelete(slotProps.data)" />
          </template>
        </Column>
      </DataTable>
    </TabPanel>
  </TabView>
  <Dialog v-model:visible="stationEditDialog" header="編輯站點資料" :modal="true" :style="{ width: '50vw' }">
    <div class="flex-column justify-content-center">
      <div class="align-items-center mb-2">
        <div>
          <label>站點名稱：</label>
          <InputText v-model="current_station.stationName" required="true" autofocus />
        </div>
        <h2>站點護士列表</h2>
        <DataTable :value="current_station.nurse_in_station" responsiveLayout="scroll">
          <Column field="employeeId" header="員工編號"></Column>
          <Column field="nurseName" header="護士姓名"></Column>
          <Column field="updatedDate" header="更新時間"></Column>
        </DataTable>
      </div>
    </div>
    <template #footer>
      <Button label="取消" icon="pi pi-times" class="p-button-text" @click="stationEditDialog = false" />
      <Button label="修改" icon="pi pi-check" class="p-button-text" @click="updateStation" />
    </template>
  </Dialog>
  <Dialog v-model:visible="nurseEditDialog" header="編輯護士資料" :modal="true" :style="{ width: '70vw' }">
    <div class="flex-column justify-content-center">
      <div class="align-items-center mb-2">
        <div class="mb-2">
          <label>員工編號：</label>
          <InputText v-model="current_nurse.employeeId" required="true" autofocus />
        </div>
        <div>
          <label>護士姓名：</label>
          <InputText v-model="current_nurse.nurseName" required="true" autofocus />
        </div>
      </div>
    </div>
    <h3>選取站點</h3>
    <div class="mb-4">
      <PickList v-model="picklist_stations" listStyle="height:35vh" dataKey="stationId">
        <template #sourceheader> 全部站點 </template>
        <template #targetheader> 已選站點 </template>
        <template #item="slotProps">
          <div class="product-item border-bottom-2">
            <div class="product-list-detail">
              <h3>{{ slotProps.item.stationName }}</h3>
            </div>
            <!-- <div class="product-list-detail">
              <h5 v-if="slotProps.item.nurse_in_station && slotProps.item.nurse_in_station != []">(目前人數：{{ slotProps.item.nurse_in_station.length }})</h5>
            </div> -->
          </div>
        </template>
      </PickList>
    </div>
    <template #footer>
      <Button label="取消" icon="pi pi-times" class="p-button-text" @click="nurseEditDialog = false" />
      <Button label="修改" icon="pi pi-check" class="p-button-text" @click="updateNurse" />
    </template>
  </Dialog>
  <Dialog v-model:visible="deleteStationDialog" :style="{ width: '450px' }" header="警告" :modal="true">
    <div class="confirmation-content">
      <i class="pi pi-exclamation-triangle mr-3" style="font-size: 2rem" />
      <span v-show="current_station.stationName"
        >是否確認刪除 <b>{{ current_station.stationName }}</b
        >?</span
      >
    </div>
    <template #footer>
      <Button label="取消" icon="pi pi-times" class="p-button-text" @click="deleteStationDialog = false" />
      <Button label="確認" icon="pi pi-check" class="p-button-text" @click="deleteItem('station')" />
    </template>
  </Dialog>
  <Dialog v-model:visible="deleteNurseDialog" :style="{ width: '450px' }" header="警告" :modal="true">
    <div class="confirmation-content">
      <i class="pi pi-exclamation-triangle mr-3" style="font-size: 2rem" />
      <span v-show="current_nurse.nurseName"
        >是否確認刪除 <b>{{ current_nurse.nurseName }}</b
        >?</span
      >
    </div>
    <template #footer>
      <Button label="取消" icon="pi pi-times" class="p-button-text" @click="deleteDialog = false" />
      <Button label="確認" icon="pi pi-check" class="p-button-text" @click="deleteItem('nurse')" />
    </template>
  </Dialog>
</template>

<script setup>
import TabView from "primevue/tabview";
import TabPanel from "primevue/tabpanel";
import InputText from "primevue/inputtext";
import Button from "primevue/button";
import PickList from "primevue/picklist";
import DataTable from "primevue/datatable";
import Column from "primevue/column";
import Dialog from "primevue/dialog";

import { onMounted, ref, watch } from "vue";
import { getStations, createStation, getNursesByStationId, updateStationById, deleteStationById, getNurses, createNurse, updateNurseById, getStationByNurseId, deleteNurseById } from "./api.js";
const msg = ref("SIMPLE SYSTEM DEMO");
const stations = ref([]);
const nurses = ref([]);
const picklist_stations = ref([]);
const stations_with_nurses = ref([]);
// Dialog
const nurseEditDialog = ref(false);
const stationEditDialog = ref(false);
const deleteStationDialog = ref(false);
const deleteNurseDialog = ref(false);
const current_station = ref({
  stationId: -1,
  stationName: "",
  updatedDate: "",
  nurse_in_station: [],
});
const current_nurse = ref({
  nurseId: -1,
  employeeId: "",
  nurseName: "",
  updatedDate: "",
});
picklist_stations.value = [
  // rest items
  [],
  // selected items
  [],
];

async function initApp() {
  await getStations().then((res) => {
    if (res.data) {
      stations.value = res.data;
      picklist_stations.value[0] = res.data;
      picklist_stations.value[1] = [];
      stations_with_nurses.value = res.data;
    }
  });
  await getNurses().then((res) => {
    nurses.value = res.data;
  });
  await updateNurseInStationList();
}

function updateNurseInStationList() {
  stations_with_nurses.value.forEach((elem, idx, arr) => {
    getNursesByStationId(elem.stationId).then((res) => {
      arr[idx].nurse_in_station = res.data;
    });
  });
}

onMounted(() => {
  initApp();
});

const initCurrentStation = () => {
  current_station.value.stationId = -1;
  current_station.value.stationName = "";
  current_station.value.updatedDate = "";
  current_station.value.nurse_in_station = [];
};

const initCurrentNurse = () => {
  current_nurse.value.nurseId = -1;
  current_nurse.value.employeeId = "";
  current_nurse.value.nurseName = "";
  current_nurse.value.createdDate = "";
  current_nurse.value.updatedDate = "";
};

function addStation() {
  createStation(current_station.value).then((res) => {
    if (res.data) {
      initCurrentStation();
      initApp();
    }
  });
}

function updateStation() {
  updateStationById(current_station.value.stationId, current_station.value).then((res) => {
    if (res.data) {
      console.log(res.data);
      if (Array.isArray(stations.value)) {
        stations.value.forEach((elem, idx, arr) => {
          if (res.data.stationId == elem.stationId) {
            arr[idx].stationName = res.data.stationName;
            arr[idx].updatedDate = res.data.updatedDate;
            stationEditDialog.value = false;
          }
        });
      }
      initCurrentStation();
    }
  });
}

function addNurse() {
  let nurseObj = { ...current_nurse.value };
  let middleList = [];
  if (Array.isArray(picklist_stations.value[1])) {
    picklist_stations.value[1].forEach((elem) => {
      middleList.push({ stationId: elem.stationId, nurseId: nurseObj.nurseId });
    });
  }
  nurseObj.middleList = middleList;
  createNurse(nurseObj).then((res) => {
    if (res.data) {
      initCurrentNurse();
      initApp();
    }
  });
}

function updateNurse() {
  console.log(current_nurse.value);
  let nurseObj = { ...current_nurse.value };
  let middleList = [];
  if (Array.isArray(picklist_stations.value[1])) {
    picklist_stations.value[1].forEach((elem) => {
      middleList.push({ stationId: elem.stationId, nurseId: nurseObj.nurseId });
    });
  }
  console.log(picklist_stations.value[1]);
  nurseObj.middleList = middleList;
  updateNurseById(nurseObj.nurseId, nurseObj).then((res) => {
    if (res.data) {
      if (Array.isArray(nurses.value)) {
        nurses.value.forEach((elem, idx, arr) => {
          if (res.data.nurseId == elem.nurseId) {
            arr[idx].nurseName = res.data.nurseName;
            nurseEditDialog.value = false;
          }
        });
      }
      initCurrentNurse();
      initApp();
    }
  });
}

const editStationDialog = (station) => {
  current_station.value = { ...station };
  stationEditDialog.value = true;
};

const editNurse = (prod) => {
  current_nurse.value = { ...prod };
  getStationByNurseId(current_nurse.value.nurseId).then((res) => {
    let rs = [...res.data];
    rs.forEach((elem) => {
      picklist_stations.value[0].forEach((elem2, idx, arr) => {
        if (elem2.stationId == elem.stationId) {
          picklist_stations.value[0].splice(idx, 1);
          console.log(picklist_stations.value[0]);
        }
      });
    });
    picklist_stations.value[1] = [...res.data];
  });
  nurseEditDialog.value = true;
};

const confirmDelete = (obj) => {
  let prod = { ...obj };
  if (prod.stationName) {
    current_station.value = prod;
    deleteStationDialog.value = true;
  } else {
    current_nurse.value = prod;
    deleteNurseDialog.value = true;
  }
};

const deleteItem = (type) => {
  if (type === "station") {
    deleteStationDialog.value = false;
    deleteStationById(current_station.value.stationId).then((res) => {
      initApp();
      initCurrentStation();
    });
  } else if (type == "nurse") {
    deleteNurseDialog.value = false;
    deleteNurseById(current_nurse.value.nurseId).then((res) => {
      initApp();
      initCurrentNurse();
    });
  }
};
</script>
<style>
body {
  text-align: center;
}
.product-item {
  display: flex;
  align-items: center;
  width: 100%;
}
.product-item .product-list-detail {
  flex: 1 1 0;
}
</style>
