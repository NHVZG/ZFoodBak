<template>
  <div>

    <div><!--class="navBar"-->
      <scrollactive ref="scrollactive" class="my-nav" :activeClass="activeClass">
        <!--<div><a href="#home"  class="scrollactive-item">Home</a></div><div class="dropdown-divider" style="margin: 0"></div>
        <div><a href="#about-us"  class="scrollactive-item">About Us</a></div><div class="dropdown-divider" style="margin: 0"></div>
        <div><a href="#portfolio"  class="scrollactive-item">Portfolio</a></div><div class="dropdown-divider" style="margin: 0"></div>
        <div><a href="#contact"  class="scrollactive-item">Contact</a></div>-->
        <slot></slot>
      </scrollactive>
    </div>

    <!--<div id="home" style="height: 100em;background-color: white;"></div>
    <div id="about-us" style="height: 100em;background-color: #8970a7;"></div>
    <div id="portfolio" style="height: 100em;background-color: #fab5b5;"></div>
    <div id="contact" style="height: 100em;background-color: greenyellow;"></div>-->
  </div>
</template>

<style scoped>

</style>

<script>
  export default{
    data () {
      return {
        msg: '这个是Home模板页',
        activeClass:'active-item',
      }
    }
  }
</script>
