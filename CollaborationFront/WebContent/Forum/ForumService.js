'use strict';

app.service('ForumService', ['$http','$q','$rootScope',
	function($http, $q, $rootScope) {

			console.log("ForumService...")

    var BASE_URL = 'http://localhost:8086/RestfulServices';

				
				 var factory = {
			        fetchAllForums: fetchAllForums,
			        createForum: createForum,
			        updateForum:updateForum,
			        AcceptedForums:AcceptedForums,
			        notAcceptedForums:notAcceptedForums,
			        accept: accept,
			        deleteForum:deleteForum
			    };
			 
			    return factory;

			    function fetchAllForums() {
			    	console.log("calling fetchAllForums ")
					return $http.get(BASE_URL + '/forum').then(function(response) {
								return response.data;
							}, null);
				};
				
				function AcceptedForums() {
					console.log("calling AcceptedForums ")

					return $http.get(BASE_URL + '/acceptedforum').then(
							function(response) {
								console.log('response');
								return response.data;
								console.log(response)
							}, null);
				};

				function notAcceptedForums() {
					console.log("calling notAcceptedForums ")

					return $http.get(BASE_URL + '/notAcceptedforum').then(
							function(response) {
								console.log(response)
								return response.data;

							}, null);
				};

				function createForum(forum){
					console.log("calling create Forum")
					return $http.post(BASE_URL + '/forum', forum) // 1
					.then(function(response) {
						return response;
					}, function(errResponse) {
						console.error('Error while creating Forum');
						return $q.reject(errResponse);
					});
				};

				function updateForum(forum){
					console.log("calling fetchAllForums ")
					return $http.put(BASE_URL + '/forum/', Forum) // 2
					.then(function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while updating Forum');
						return $q.reject(errResponse);
					});
				};
				function accept(Forum) {
					console.log("calling accept Forums ")
					return $http.put(BASE_URL + '/acceptForum', Forum) // 2
					.then(function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while accepting Forum');
						return $q.reject(errResponse);
					});
				};
				function deleteForum(forumid){
					console.log("Deleting Forum Request");
					return $http.delete(BASE_URL + '/forum/'+forumid).then(function(response){
							
						return response.data;
							},function(errResponse) {
								console.error('Error while deleting Forums request');
								return $q.reject(errResponse);
							});
			
				};
				

		} ]);