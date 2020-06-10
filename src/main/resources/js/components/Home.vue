<template>
  <b-container class="main-container" fluid>
    <b-container   class="form-container"> 
      
      <b-form @submit.prevent="onSubmit" class="pt-3 pb-3">
      <b-form-group
        id="input-group-1"
        label="Ритм сердца:"
        label-for="input-1"
        :validated="rhythm.selected>0"
      >
        <b-form-select id="input-1" v-model="rhythm.selected" :options="rhythm.types"></b-form-select>
      </b-form-group>

      <h5>Трикуспидальный клапан</h5>
      <b-form-group id="input-group-2" 
        label="Регургитация (недостаточность):" 
        label-for="input-2"
        :validated="failure.triSelected>0"
      >
        <b-form-select id="input-2" v-model="failure.triSelected" :options="types"></b-form-select>
      </b-form-group>

      <h5>Клапан лёгочной артерии</h5>
      <b-form-group id="input-group-3" 
        label="Регургитация (недостаточность):" 
        label-for="input-3"
        :validated="failure.artSelected>0"
      >
        <b-form-select id="input-3" v-model="failure.artSeleted" :options="types"></b-form-select>
      </b-form-group>

      <h5>Митральный клапан</h5>
      <b-form-group
        id="input-group-4"
        label="Площадь отверстия:"
        label-for="input-4"
        :validated="mitralValve.sqr>0"
      >
        <b-form-input
          id="input-4"
          v-model="mitralValve.sqr"
          type="number"
          required
          placeholder="в см²"
          step="0.1"
        ></b-form-input>
        <b-form-invalid-feedback :state="mitralValve.sqr>0">Заполните поле</b-form-invalid-feedback>
      </b-form-group>

      <b-form-group
        id="input-group-5"
        label="Максимальный градиент:"
        label-for="input-5"
      >
        <b-form-input
          id="input-5"
          v-model="mitralValve.maxGrad"
          type="number"
          required
          placeholder="в мм рт. ст"
          :validated="mitralValve.maxGrad>0"
          step="0.1"
        ></b-form-input>
        <b-form-invalid-feedback :state="mitralValve.maxGrad>0">Заполните поле</b-form-invalid-feedback>
      </b-form-group>

      <b-form-group id="input-group-6" 
        label="Регургитация (недостаточность):" 
        label-for="input-6"
        :validated="mitralValve.selected>0"
      >
        <b-form-select id="input-6" v-model="mitralValve.selected" :options="types"></b-form-select>
      </b-form-group>

      <h5>Левый желудочек</h5>
      <b-form-group
        id="input-group-7"
        label="Фракция выброса по Симпсону:"
        label-for="input-7"
        :validated="fractation>0"
      >
        <b-form-input
          id="input-7"
          v-model="fractation"
          type="number"
          required
          placeholder="в %"
          step="0.1"
        ></b-form-input>
        <b-form-invalid-feedback :state="fractation>0">Заполните поле</b-form-invalid-feedback>
      </b-form-group>

      <h5>Аортальный клапан</h5>
      <b-form-group
        id="input-group-8"
        label="Площадь отверстия:"
        label-for="input-8"
        :validated="aorticValve.sqr>0"
      >
        <b-form-input
          id="input-8"
          v-model="aorticValve.sqr"
          type="number"
          required
          placeholder="в см²"
          step="0.1"
        ></b-form-input>
        <b-form-invalid-feedback :state="aorticValve.sqr>0">Заполните поле</b-form-invalid-feedback>
      </b-form-group>

      <b-form-group
        id="input-group-9"
        label="Максимальный градиент:"
        label-for="input-9"
        :validated="aorticValve.maxGrad>0"
      >
        <b-form-input
          id="input-9"
          v-model="aorticValve.maxGrad"
          type="number"
          required
          placeholder="в мм рт. ст"
          step="0.1"
        ></b-form-input>
        <b-form-invalid-feedback :state="aorticValve.maxGrad>0">Заполните поле</b-form-invalid-feedback>
      </b-form-group>

      <b-form-group id="input-group-10" 
        label="Регургитация (недостаточность):" 
        label-for="input-10"
        :validated="aorticValve.selected>0"
      >
        <b-form-select id="input-10" v-model="aorticValve.selected" :options="types"></b-form-select>
      </b-form-group>

      <h5>Аорта</h5>
      <b-form-group
        id="input-group-11"
        label="Диаметр аорты в области синусов Вальсальвы:"
        label-for="input-11"
        :validated="aorta>0"
      >
        <b-form-input
          id="input-11"
          v-model="aorta"
          type="number"
          required
          step="0.1"
        ></b-form-input>
        <b-form-invalid-feedback :state="aorta>0">Заполните поле</b-form-invalid-feedback>
      </b-form-group>

      <h5>Полости сердца</h5>
      <b-form-group id="input-group-12"
        :validated="bloodСlot.exis!=null"
        label="Наличие тромбов в полостях сердца:" 
        label-for="input-12"
      >
        <b-form-select id="input-12" v-model="bloodСlot.exist" :options="bloodСlot.types"></b-form-select>
      </b-form-group>

      <b-row>
        <div class="mx-auto">
          <b-button type="submit" variant="outline-info">Результат</b-button>
        </div>
      </b-row>

    </b-form>

    </b-container>
  </b-container>
</template>

<script>
export default {
    name:"home",
    data(){
      return{
        types:[
            {value:0,text:"Выберете тип"},
            {value:1,text:"0-I"},
            {value:2,text:"0-I"},
            {value:3,text:"I"},
            {value:4,text:"I-II"},
            {value:5,text:"II"},
            {value:6,text:"II-III"},
            {value:7,text:"III"},
            {value:8,text:"III-IV"},
            {value:9,text:"IV"}           
        ],
        rhythm:{
          selected:0,
          types:[
            {value:0,text:"Выберете тип"},
            {value:1, text:"Синусовый ритм"},
            {value:2, text:"Фибрилляция предсердий"},
            {value:4, text:"Трепетание предсердий"},
            {value:5, text:"Кардиостимулияция"}
          ]
        },
        failure:{
          triSelected:0,
          artSeleted:0
        },
        mitralValve:{
          sqr:"",
          maxGrad:"",
          selected:0
        },
        fractation:"",
        aorticValve:{
          sqr:"",
          maxGrad:"",
          selected:0
        },
        aorta:"",
        bloodСlot:{
          exist:null,
          types:[
            {value:null,text:"Выберете тип"},
            {value:true, text:"да"},
            {value:false, text:"нет"},]
        }
      }
    },
    methods:{
      onSubmit(){
        let message="Консультация нужна";
        if  (this.fractation<=25 ||
              (
                (this.rhythm.selected==0 || this.rhythm.selected==1 || this.rhythm.selected==5) && 
                (this.failure.triSelected==0 || this.failure.triSelected==1 || this.failure.triSelected!=2 || this.failure.triSelected!=3 || this.failure.triSelected!=4 || this.failure.triSelected!=5) && 
                (this.failure.artSeleted==0 || this.failure.artSeleted==1 || this.failure.artSeleted!=2 || this.failure.artSeleted!=3 || this.failure.artSeleted!=4 || this.failure.artSeleted!=5) && 
                this.mitralValve.sqr>=2 && 
                this.mitralValve.maxGrad<=10 && 
                (this.mitralValve.selected==0 || this.mitralValve.selected==1 || this.mitralValve.selected!=2 || this.mitralValve.selected!=3 || this.mitralValve.selected!=4 || this.mitralValve.selected!=5) && 
                this.aorticValve.sqr>=1 && 
                this.aorticValve.maxGrad<=50 && 
                (this.aorticValve.selected==0 || this.aorticValve.selected==1 || this.aorticValve.selected!=2 || this.aorticValve.selected!=3 || this.aorticValve.selected!=4 || this.aorticValve.selected!=5) && 
                this.aorta<=5 && 
                (this.bloodСlot.exist==null || !this.bloodСlot.exist)
              )
            )
          message="консультация не нужна";
        alert(message);
      }
    }
}
</script>

<style>
.main-container{
  background-color: azure;
  min-height: 100vh;
}
.form-container{
  background-color: aliceblue;
  min-height: 100vh;
}
</style>