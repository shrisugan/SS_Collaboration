'use strict';

app.service('CommentService', ['$http','$q','$rootScope',
	function($http, $q, $rootScope) {

			console.log("CommentService...") 

			var BASE_URL =' http://localhost:8086/RestfulServices'

				 var factory = {
			        
			        createComment: createComment
			        
			       
			    };
			 
			    return factory;

				function createComment(comment) {
					console.log("calling create Comment")
					console.log(comment)
					return $http.post(BASE_URL + '/comments', comment) // 1
					.then(function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while creating Comments');
						return $q.reject(errResponse);
					});
				};
		

		} ]);