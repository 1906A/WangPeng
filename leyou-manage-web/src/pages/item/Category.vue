<template>
  <v-card>
      <v-flex xs12 sm10>
        <v-tree url="/item/category/list"
                :isEdit="isEdit"
                @handleAdd="handleAdd"
                @handleEdit="handleEdit"
                @handleDelete="handleDelete"
                @handleClick="handleClick"
        />
      </v-flex>
  </v-card>
</template>

<script>
  import {treeData} from "../../mockDB";

  export default {
    name: "category",
    data() {
      return {
        isEdit:true,
        treeData:treeData
      }
    },
    methods: {
     handleAdd(node) {
        console.log("add .... ");
       /*  console.log(node);
        this.$http.post("/item/category/add",node)
          .then((res)=>{
            alert("添加成功");
          }

        ).catch((error)=>{
          alert("请求失败")
        })*/

      },
      handleEdit(node) {
        //console.log("edit... id: " + id + ", name: " + name)

        console.log("------------------------------");
        console.log(node);
        console.log("------------------------------");

        if(node.id==0){//添加
          this.$http.post("/item/category/add",node)
            .then((res)=>{
              alert("添加成功");
              }
            ).catch((error)=>{
            console.log(error);
              alert("添加失败")
          })
          window.location.reload();
        }else{//修改

          this.$http.post("/item/category/updateCategory",node)
            .then((res)=>{
              if(res.data=='SUCC'){
                alert('修改成功');
                window.location.reload();
              }else if(res.data=='FALL'){
                alert('修改失败');
              }
              }

            ).catch((error)=>{
            alert("请求失败")
          })
        }

      },
      handleDelete(id) {
        console.log("delete ... " + id)

        this.$http.get("/item/category/deleteById",{
          params:{id:id}
        }).then((res)=>{
          if(res.data=="SUCC"){
            alert("删除成功")
          }else if(res.date="FALL"){
            alert("删除失败")
          }

        }).catch((error)=>{
          alert("请求失败")

        })

        /*this.$http.get("/item/category/deleteById?id="+id)
          .then((res)=>{
            if(res.data=="SUCC"){
              alert("删除成功")
            }else if(res.date="FALL"){
              alert("删除失败")
            }
          }).catch((error)=>{
              alert("请求失败")
        })*/

        //此方法有问题
        /*this.$http.post("/item/category/deleteById",{
              params:{id:id}
        }).then((res)=>{
              if(res.data=="SUCC"){
              alert("删除成功")
            }else if(res.date="FALL"){
              alert("删除失败")
            }
          }
          ).catch((error)=>{
          alert("请求失败")
        })*/
      },
      handleClick(node) {
        console.log(node)
      }
    }
  };
</script>

<style scoped>

</style>
