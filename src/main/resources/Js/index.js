window.onload = function () {


    const userSearch = document.getElementById('search');
    const userFilter = document.getElementById('filter');
    const nextButton = document.querySelector('#right')
    const backButton = document.querySelector('#left')




    function search(context){
        const http = new XMLHttpRequest();
        http.onreadystatechange=function(){


            const response = http.responseText;
            if (response != null){
                console.log(http.responseText);
                document.getElementById("mainbody").innerHTML = http.responseText
            }

        }

        http.open("POST","/search", true);
        http.send(context);

    }

    function filter(context){
        const http = new XMLHttpRequest();


        http.onreadystatechange = function () {

            const response = http.responseText;
            if (response != null){
                console.log(http.responseText);
                document.getElementById("mainbody").innerHTML = http.responseText
            }

        }
        http.open("POST","/filter", true);
        http.send(context);

    }

    function next(){
        const http = new XMLHttpRequest();



        http.onreadystatechange = function () {

            const response = http.responseText;

            if(response != null){

                 document.getElementById("image-src").innerHTML = http.responseText
            }


        }

        http.open("GET", "/next", true);
        http.send();
    }

     function back (){

         const http = new XMLHttpRequest();



         http.onreadystatechange = function () {

             const response = http.responseText;

             if(response != null){

                 document.getElementById("image-src").innerHTML = http.responseText
             }


         }

         http.open("GET", "/next", true);
         http.send();

    }


    ////////////////////////

   if(userSearch != null){
       userSearch.addEventListener('keyup', (event)=>{

           const context = event.target.value;
           search(context);
       });
   }

   if (userFilter != null){
       userFilter.addEventListener('change', (event) => {

           const context = event.target.value;
           filter(context);
       });
   }

   if (nextButton != null){
       nextButton.addEventListener('click', () =>{
           next();
       })

   }

    if (backButton != null){
        backButton.addEventListener('click', () =>{
            back();
        })

    }














}
