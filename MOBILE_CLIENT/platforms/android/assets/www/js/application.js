/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

var ROOT_URL  = "http://env-3166114.jelastic.servint.net/rest/movieservice/movieList";
var LIST_MOVIE = [];
var STARTING_POINT = 0;

function Movie() {
   this.id = "";
   this.name = "";
   this.director = "";
   this.actor = "";
   this.year = "";
   this.status = "";
   this.thumbnail = "";
   this.description = "";
}

/* Function to get the HTML for each item*/
function getItemHTML(kindItem, movieItem) {
   var itemHTML = "<li ";
   switch(kindItem) {
      case 1: /* This is the first child */
         itemHTML += "class='ui-li-has-thumb ui-first-child'>";
         break;
      case 2: /* This is the last child */
         itemHTML += "class='ui-li-has-thumb ui-last-child'>";
         break;
      default:
         itemHTML += "class='ui-li-has-thumb'>";
         break;
   }
   itemHTML += "<a class='movie-button ui-btn ui-btn-icon-right ui-icon-carat-r'>";
   itemHTML += "<img src=" + "'" + movieItem.thumbnail + "'" + " class='ui-li-thumb' />";
   itemHTML += "<h2>" + movieItem.name + "</h2>";
   itemHTML += "<div class='movie-description'>";
   itemHTML += "<h3>" + movieItem.actor + "</h3>";
   itemHTML += "<p>" + "90 phut" + "</p>";
   itemHTML += "</div>";
   itemHTML += "<p class='ui-li-aside'>" + movieItem.status + "</p>";
   itemHTML += "</a></li>";

   return itemHTML;
}

/* Function to get the HTML for list of items */
function getListHTML(starting_point, movie_length) {
   var listHTML = "";

   if(movie_length + starting_point > LIST_MOVIE.length) {
      movie_length = LIST_MOVIE.length - starting_point;
   }
   if(movie_length > 0) {
      listHTML += getItemHTML(1, LIST_MOVIE[starting_point]);

      for(var i=starting_point + 1; i<starting_point + movie_length-1; ++i) {
         listHTML += getItemHTML(3, LIST_MOVIE[i]);
      }

      listHTML += getItemHTML(2, LIST_MOVIE[starting_point + movie_length - 1]);
   }
   return listHTML;
}

function attachListMovie(jsondata) {
   $.each(jsondata, function(key, value) {
      console.log(value.ID);
      var movieItem = new Movie();
   	movieItem.id = value.ID;
   	movieItem.name = value.NAME;
   	movieItem.director = value.DIRECTOR;
   	movieItem.actor = value.ACTOR;
   	movieItem.year = value.YEAR;
   	movieItem.status = value.STATUS;
   	movieItem.thumbnail = value.THUMBNAIL;
   	movieItem.description = value.DESCRIPTION;

   	LIST_MOVIE.push(movieItem);
   });
   console.log(LIST_MOVIE.length);
}

var Application  = {
   initApplication: function() {
      $.ajax({
         type: "GET",
         url: ROOT_URL,
         datatype: "json",
         success: function(jsondata) {
            attachListMovie(jsondata);
            Application.initMovieList();
         }
      });

      Application.initSearchButton();

      /* Back button listener */
      $(document).on("backbutton", function() {
         Application.initMovieList();
      });
   },
   initMovieList: function() {
      $("#movie-content").load("movie-list.html #movie-list", function () {
         STARTING_POINT = 0;
         $("#movie-list").append(getListHTML(STARTING_POINT, 12));
         STARTING_POINT = STARTING_POINT + 12;

         $(window).scroll(function () {
            console.log('scroll');
            var height = $(window).height();
            var scrollTop = $(window).scrollTop();
            if (scrollTop + height >= (height - 100)) {
               /* now load more list-items because the user is within 100px of the bottom of the page */
               console.log('You Hit Bottom!');
               $("#movie-list").append(getListHTML(STARTING_POINT, 6));
               STARTING_POINT = STARTING_POINT + 6;
            }
         });
         $('.movie-button').click(function(){
            $("#movie-content").load("movie-details.html #movie-frame");
         });
      });
   },
   initSearchButton: function() {
       /* Show and hide the search form */
       var showform = false;
       var tempTitle = $(".ui-title").html();
       $("#searchbutton").click(function(){
           if( showform == false ) {
               $("#searchparentform").show();
               $(".ui-title").html("");
               showform = true;
           } else {
               $("#searchparentform").hide();
               $(".ui-title").html(tempTitle);
               showform = false;
           }
       });
    }
 };