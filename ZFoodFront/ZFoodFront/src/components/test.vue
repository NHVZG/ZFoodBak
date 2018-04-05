<template>
    <div class="cla">
      <vue-datepicker-local v-model="range"></vue-datepicker-local>
      {{ids}}
        {{msg}}
      <!--<img :src="imurl" alt="">-->

    </div>
</template>

<style scoped>
  .cla>>>.datepicker-range .datepicker-popup{
   width:435px;
  }
</style>



<script>
  import VueDatepickerLocal from 'vue-datepicker-local'

  export default{
        data () {
            return {
                msg: '这个是Home模板页',
                imurl:'static/img/user/e6879869Me55eM4d10Mada5M65eb7855dc46.jpg',
                ids:this.$route.params.id,
                range:[new Date(),new Date()],
            }
        },
      methods:{
            f(){
                alert('ss');
            },
         move({x = 0, y = 0}) {
            return [x, y];
          }

    },
    components: {
      VueDatepickerLocal
    },
      mounted(){
        let x;
        console.log([1][0]);// 代表[1]数组的第0个元素    [1,2,3][2]=3
        if ([1][0] === undefined) {
          x = f();
        } else {
          x = [1][0];
          alert(x);
        }
        alert(this.move({x:2,y:3}));

        let a={z:1,x:2,y:3,f:4};
        let {z,y,f}=a;
        alert(z+' '+y+' '+f);
      },
      created(){
          //alert(this.$getTokenData('userId'));
          alert(this.$route.params.id);
          this.ids=this.$route.params.id;
      }
    }
</script>
