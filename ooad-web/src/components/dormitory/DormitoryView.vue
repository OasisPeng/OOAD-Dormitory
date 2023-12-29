<template>
  <div id="app">
    <div id="container"></div>
  </div>
</template>

<script>

 import AMapLoader from "@amap/amap-jsapi-loader";



export default {

  name: "App",
  data() {
    return {
      map: null,
      path_hupan:null,
      polygon1: null,
      path_liyuan:null,
      polygon2: null,
      path_erqi:null,
      polygon3: null,
      path_xingyuan:null,
      polygon4: null
    };
  },
  mounted() {
    this.initMap()
  },

  methods: {
    tohupan(){
      console.log('ok')
      this.$router.push( '/HuPanDormitoryView.vue')
      console.log('ok')
    },
    toerqi(){
      console.log('ok')
      this.$router.push( '/ErQiDormitoryView.vue')
      console.log('ok')
    },
    toliyuan(){
      console.log('ok')
      this.$router.push( '/LiYuanDormitoryView.vue')
      console.log('ok')
    },
    toxingyuan(){
      console.log('ok')
      this.$router.push( '/XingYuanDormitoryView.vue')
      console.log('ok')
    },
    initMap() {

      AMapLoader.load({
        key: "a820dc1fc9aafb908c7969a6ec257fec", // 申请好的Web端开发者Key，首次调用 load 时必填
        version: "2.0", // 指定要加载的 JSAPI 的版本，缺省时默认为 1.4.15
        plugins: [""], // 需要使用的的插件列表，如比例尺'AMap.Scale'等
      }).then((AMap) => {
        this.map = new AMap.Map("container", {
          //设置地图容器id
          viewMode: "2D", //是否为3D地图模式
          zoom: 17, //初始化地图级别
          center: [113.998133,22.603293], //初始化地图中心点位置
        });
        this.path_hupan = [
          new AMap.LngLat(113.998294,22.598969),
          new AMap.LngLat(113.998412,22.599786),
          new AMap.LngLat(113.998036,22.600178),
          new AMap.LngLat(113.997838,22.600396),
          new AMap.LngLat(113.998079,22.600599),
          new AMap.LngLat(113.998911,22.600757),
          new AMap.LngLat(113.999136,22.601114),
          new AMap.LngLat(113.999463,22.601153),
          new AMap.LngLat(113.999705,22.600841),
          new AMap.LngLat(113.998734,22.600401),
          new AMap.LngLat(113.998975,22.598954)
        ];
        this.path_liyuan = [
          new AMap.LngLat(113.999686,22.604141),
          new AMap.LngLat(113.999992,22.604904),
          new AMap.LngLat(114.00026,22.604869),
          new AMap.LngLat(114.000394,22.605449),
          new AMap.LngLat(113.999514,22.605647),
          new AMap.LngLat(113.999214,22.604255)
        ];
        this.path_erqi = [
          new AMap.LngLat(113.996821,22.601887),
          new AMap.LngLat(113.997036,22.600985),
          new AMap.LngLat(113.999745,22.601391),
          new AMap.LngLat(114.001462,22.602984),
          new AMap.LngLat(114.000534,22.603239),
          new AMap.LngLat(113.996832,22.601882)
        ];
        this.path_xingyuan = [
          new AMap.LngLat(114.00136,22.608191),
          new AMap.LngLat(114.001649,22.608508),
          new AMap.LngLat(114.001692,22.608761),
          new AMap.LngLat(114.001998,22.608998),
          new AMap.LngLat(114.00298,22.608107),
          new AMap.LngLat(114.001982,22.607498),
          new AMap.LngLat(114.001349,22.608151)
        ];


        this.polygon1 = new AMap.Polygon({
          path: this.path_hupan,
          fillColor: '#ccebc5',
          strokeOpacity: 1,
          fillOpacity: 0.5,
          strokeColor: '#2b8cbe',
          strokeWeight: 1,
          strokeStyle: 'dashed',
          strokeDasharray: [5, 5],
        });
        this.polygon2 = new AMap.Polygon({
          path: this.path_liyuan,
          fillColor: '#ccebc5',
          strokeOpacity: 1,
          fillOpacity: 0.5,
          strokeColor: '#2b8cbe',
          strokeWeight: 1,
          strokeStyle: 'dashed',
          strokeDasharray: [5, 5],
        });
        this.polygon3 = new AMap.Polygon({
          path: this.path_erqi,
          fillColor: '#ccebc5',
          strokeOpacity: 1,
          fillOpacity: 0.5,
          strokeColor: '#2b8cbe',
          strokeWeight: 1,
          strokeStyle: 'dashed',
          strokeDasharray: [5, 5],
        });
        this.polygon4 = new AMap.Polygon({
          path: this.path_xingyuan,
          fillColor: '#ccebc5',
          strokeOpacity: 1,
          fillOpacity: 0.5,
          strokeColor: '#2b8cbe',
          strokeWeight: 1,
          strokeStyle: 'dashed',
          strokeDasharray: [5, 5],
        });

        this.polygon1.on('mouseover', () => {
          this.polygon1.setOptions({
            fillOpacity: 0.7,
            fillColor: '#7bccc4'
          })
        })
        this.polygon1.on('mouseout', () => {
          this.polygon1.setOptions({
            fillOpacity: 0.5,
            fillColor: '#ccebc5'

          })
        })
        this.polygon2.on('mouseover', () => {
          this.polygon2.setOptions({
            fillOpacity: 0.7,
            fillColor: '#7bccc4'
          })
        })
        this.polygon2.on('mouseout', () => {
          this.polygon2.setOptions({
            fillOpacity: 0.5,
            fillColor: '#ccebc5'

          })
        })
        this.polygon3.on('mouseover', () => {
          this.polygon3.setOptions({
            fillOpacity: 0.7,
            fillColor: '#7bccc4'
          })
        })
        this.polygon3.on('mouseout', () => {
          this.polygon3.setOptions({
            fillOpacity: 0.5,
            fillColor: '#ccebc5'

          })
        })
        this.polygon4.on('mouseover', () => {
          this.polygon4.setOptions({
            fillOpacity: 0.7,
            fillColor: '#7bccc4'
          })
        })
        this.polygon4.on('mouseout', () => {
          this.polygon4.setOptions({
            fillOpacity: 0.5,
            fillColor: '#ccebc5'

          })
        })
        this.map.add(this.polygon1);
        this.map.add(this.polygon2);
        this.map.add(this.polygon3);
        this.map.add(this.polygon4);

        this.polygon1.on('click', () => {
          console.log('ok1');
          this.tohupan();
        });
        this.polygon3.on('click', () => {
          console.log('ok1');
          this.toerqi();
        });
        this.polygon2.on('click', () => {
          console.log('ok1');
          this.toliyuan();
        });
        this.polygon4.on('click', () => {
          console.log('ok1');
          this.toxingyuan();
        });
// 创建信息窗口1
        var infoWindow1 = new AMap.InfoWindow({
          anchor: 'middle-right',
          content: '宿舍选择一共有四个区域，请点击区域块查看详细信息',
        });
        var position1 = [113.997553,22.603714];
        infoWindow1.open(this.map, position1);




      }).catch((e) => {
        console.error("Failed to load AMap:", e);
      });
      // 创建多边形 Polygon 实例

      //多边形 Polygon对象添加到 Map


    },


  }
};
</script>

<style>
#app {
  margin: 0;
  padding: 0;
}
#container {
  padding: 0px;
  margin: 0px;
  width: 100%;
  height: 100vh;
}
</style>
