'use strict';

app.service('BlogCommentService', [ '$http', '$q', '$rootScope',
		function($http, $q, $rootScope) {

			console.log("BlogCommentService...")

			var BASE_URL = ' http://localhost:8086/RestfulServices'

			var factory = {

				fetchAllBlogComments : fetchAllBlogComments,
				createBlogComment : createBlogComment,

			};

			return factory;
			
			
			function fetchAllBlogComments(id) {
				console.log("calling fetchAllBlogComments ")
				return $http.get(BASE_URL + '/blogcomments/'+id).then(
						function(response) {
							console.log(response)
							return response.data;
						}, null);
			};

			function createBlogComment(blogcomment) {
				console.log("calling create BlogComment")
				console.log(blogcomment)
				return $http.post(BASE_URL + '/blogcomments', blogcomment) // 1
				.then(function(response) {
					return response.data;
				}, function(errResponse) {
					console.error('Error while creating BlogComments');
					return $q.reject(errResponse);
				});
			}
			;

		} ]);