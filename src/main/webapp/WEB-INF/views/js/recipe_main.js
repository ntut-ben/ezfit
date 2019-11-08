// window.onload=function(){
//     changeImgSize();
// }
// function changeImgSize(){
//     var getContainer=document.getElementById('imgcontainer');//父元素div
//     var getIMG=getContainer.getElementsByTagName('img')[0];
//     var fw=getContainer.offsetWidth-(2*getContainer.clientLeft);
//     var fh=getContainer.offsetHeight-(2*getContainer.clientTop);
//     var iw=getIMG.width;
//     var ih=getIMG.height;
//     var m=iw/fw;//图片与父元素宽度比
//     var n=ih/fh;//图片与父元素高度比
//     if(m>=1&&n<=1)//图片比父元素宽 或者图片比父元素短
//     {
// //            iw=Math.ceil(iw/m);
// //            ih=Math.ceil(ih/m);
// //            getIMG.width=iw;
// //            getIMG.height=ih;
//         iw=Math.ceil(iw/n);
//         ih=Math.ceil(ih/n);
//         getIMG.width=iw;
//         getIMG.height=ih;
//     }
//     else if(m<=1&&n>=1)//图片比父元素窄 或者图片比父元素高
//     {
//         iw=Math.ceil(iw/m);
//         ih=Math.ceil(ih/m);
//         getIMG.width=iw;
//         getIMG.height=ih;
//     }
//     else if(m>=1&&n>=1)
//     {

//         getMAX=Math.min(m,n);
//         iw=Math.ceil(iw/getMAX);
//         ih=Math.ceil(ih/getMAX);
//         getIMG.width=iw;
//         getIMG.height=ih;
//     }
// //        if(getIMG.height<fh)
// //        {
// //            var getDistance=Math.floor((fh-getIMG.height)/2);
//      //   getIMG.style.marginTop=getDistance.toString()+"px";
//     var getDistance;
//     var getDistance2;
//     if(fh>getIMG.height){
//         getDistance=Math.floor((fh-getIMG.height)/2);
//         getIMG.style.marginTop=getDistance.toString()+"px";
//     }else {
//         getDistance=Math.floor((getIMG.height-fh)/2);
//         getIMG.style.marginTop="-"+getDistance.toString()+"px";
//     }

//     if(fw>getIMG.width){
//         getDistance2=Math.floor((fw-getIMG.width)/2);
//         getIMG.style.marginLeft=getDistance2.toString()+"px";
//     }else {
//         getDistance2=Math.floor((getIMG.width-fw)/2);
//         getIMG.style.marginLeft="-"+getDistance2.toString()+"px";
//     }

// }
