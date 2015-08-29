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

var Application  = {
   initApplication: function() {
      Application.initMovieList();
      Application.initSearchButton();

      /* Back button listener */
      $(document).on("backbutton", function() {
         $("#movie-content").load("movie-list.html #movie-list", function () {
            $('.movie-button').click(function(){
               $("#movie-content").load("movie-details.html #movie-frame");
            });
         });
      });
   },
   initMovieList: function() {
      $("#movie-content").load("movie-list.html #movie-list", function () {
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