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
var CURRENT_POINT = 0;
var PAGE_NUMBER = 0;

/////////////////////////////////////////////////////////////////////////////////////
// Declare your objects at here
/////////////////////////////////////////////////////////////////////////////////////
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

/////////////////////////////////////////////////////////////////////////////////////
// The main function
/////////////////////////////////////////////////////////////////////////////////////
var Application  = {
   initApplication: function() {
      getMovieListFromServerByPagination();
      Application.initSearchButton();

      /* Back button listener */
      $(document).on("backbutton", function() {
         CURRENT_POINT = 0;
         PAGE_NUMBER = 1;
         Application.initMovieList();
      });
   },
   initMovieList: function() {
      $("#movie-content").load("movie-list.html #movie-list", function () {
         $("#movie-list").append(getListHTML());

         $(window).scroll(function () {
            console.log('scroll');
            var scrollHeight = $(document).height();
            var scrollPosition = $(window).height() + $(window).scrollTop();
            if ((scrollHeight - scrollPosition) / scrollHeight == 0) {
               console.log('You Hit Bottom!');
               getMovieListFromServerByPagination();
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
   itemHTML += "<p>" + "90 phút" + "</p>";
   itemHTML += "</div>";
   itemHTML += "<p class='ui-li-aside'>" + movieItem.status + "</p>";
   itemHTML += "</a></li>";
   return itemHTML;
}

/* Function to get the HTML for list of items */
function getListHTML() {
   var listHTML = "";
    if(LIST_MOVIE.length > 0 && CURRENT_POINT < LIST_MOVIE.length - 1) {
      if( PAGE_NUMBER == 1 ) {
         listHTML += getItemHTML(1, LIST_MOVIE[0]);
      }
       for(var i=CURRENT_POINT + 1; i<LIST_MOVIE.length-1; ++i) {
         listHTML += getItemHTML(3, LIST_MOVIE[i]);
      }
      listHTML += getItemHTML(2, LIST_MOVIE[LIST_MOVIE.length - 1]);
      CURRENT_POINT = LIST_MOVIE.length - 1;
   }
   return listHTML;
}

/* Loading message */
function showLoadingMessage() {
   $.mobile.loading( "show", {
      text: "Đang tải ...",
      textVisible: "true",
      theme: $.mobile.loader.prototype.options.theme,
      textOnly: "false",
      html: ""
   });
}

function hideLoadingMessage() {
   $.mobile.loading("hide");
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

function getMovieListFromServerByPagination() {
   showLoadingMessage();
   PAGE_NUMBER++;
   $.ajax({
      type: "GET",
      url: ROOT_URL + "/" + PAGE_NUMBER,
      datatype: "json",
      success: function(jsondata) {
         attachListMovie(jsondata);
         hideLoadingMessage();
         if(PAGE_NUMBER == 1) {
            Application.initMovieList();
         } else {
            $("#movie-list").append(getListHTML());

            $('.movie-button').click(function(){
               $("#movie-content").load("movie-details.html #movie-frame");
            });
         }
      }
   });
}
