'use strict';
 
app.service('BlogService', ['$http', '$q', function($http, $q){
	console.log("BlogService...")
    var BASE_URL = 'http://localhost:8086/RestfulServices';

	 var factory = {
		        fetchAllBlogs: fetchAllBlogs,
		        createBlog: createBlog,
		        updateBlog:updateBlog,
		        AcceptedBlogs : AcceptedBlogs,
				notAcceptedBlogs : notAcceptedBlogs,
				accept: accept,
		        deleteBlog:deleteBlog,
		        getBlogbyId:getBlogbyId
		    };
		 
		    return factory;
		 
		    function fetchAllBlogs() {
				console.log("calling fetchAllblogs ")
				return $http.get(BASE_URL+'/Blog').then(function(response) {
					return response.data;
				}, null);
			};
			function AcceptedBlogs() {
				console.log("calling AcceptedBlogs ")

				return $http.get(BASE_URL + '/acceptedblog').then(
						function(response) {
							console.log('response');
							return response.data;
							console.log(response)
						}, null);
			};
			function notAcceptedBlogs() {
				console.log("calling notAcceptedBlogs ")

				return $http.get(BASE_URL +'/notAcceptedblog').then(
						function(response) {
							console.log(response)
							return response.data;

						}, null);
			};
			function getBlogbyId(blogId) {
				console.log("calling create Blog")
				return $http.get(BASE_URL+'/getBlog/'+blogId) 
				.then(function(response) {
					return response.data;
				}, function(errResponse) {
					console.error('Error while creating Blog');
					return $q.reject(errResponse);
				});
			};
			function createBlog(blog) {
				console.log("calling create Blog")
				return $http.post(BASE_URL+'/insertBlog',blog) 
				.then(function(response) {
					return response.data;
				}, function(errResponse) {
					console.error('Error while creating Blog');
					return $q.reject(errResponse);
				});
			};

		 
			function updateBlog(Blog) {
				console.log("calling fetchAllBlogs ")
				return $http.put(BASE_URL+'/updateBlog/',Blog) 
				.then(function(response) {
					return response.data;
				}, function(errResponse) {
					console.error('Error while updating Blog');
					return $q.reject(errResponse);
				});
			};
			function accept(Blog) {
				console.log("calling accept Blogs ")
				return $http.put(BASE_URL +'/acceptBlog',Blog) 
				.then(function(response) {
					return response.data;
				}, function(errResponse) {
					console.error('Error while accepting Blog');
					return $q.reject(errResponse);
				});
			};
		    function deleteBlog(blogid) {
		    	console.log("Deleting Blog Request");
				return $http.delete(BASE_URL + '/deleteBlog/'+blogid).then(function(response){
						
					return response.data;
						},function(errResponse) {
							console.error('Error while deleting Blog request');
							return $q.reject(errResponse);
						});
		
			};

}]);