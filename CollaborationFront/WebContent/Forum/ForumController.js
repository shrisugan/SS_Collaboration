'use strict';


app.controller('ForumController', ['$scope','ForumService','$location','$rootScope','$cookieStore','$http',
	function($scope, ForumService, $location, $rootScope, $cookieStore,$http) {
	console.log("ForumController...")
	var self = this;
	
	this.forum={forumid:'',forumname : '',forumcontent : '',userid:'',username:'',
		errorCode : '',
		errorMessage : ''};
	
	
	self.forums = [];
	self.submit = submit;
	 self.accept = accept;
	    self.get = get;

	 
	 self.AcceptedForums = AcceptedForums;
	 self.notAcceptedForums = notAcceptedForums;
	 self.adminGet = adminGet;
	 self.rejectForum = rejectForum;
	 
	 fetchAllForums();
		AcceptedForums();
		reset();
		
		function fetchAllForums() {
			console.log("fetchingAllForums...")
			ForumService.fetchAllForums().then(function(d) {
				self.forums = d;
				console.log(self.forums)
			}, function(errResponse) {
				console.error('Error while fetching Forums');
			});
		};
		
		function AcceptedForums() {
			console.log("AcceptedForums...")
			ForumService.AcceptedForums().then(function(d) {
								
				console.log(d)
								self.forumsAccept = d;
							},
							function(errResponse) {
								console.error('Error while creating Acceptedforums.');
							});
		};
		function notAcceptedForums() {
			console.log("notAcceptedForums...")
			ForumService.notAcceptedForums().then(function(d) {
							
				console.log(d)
								self.forumsNotAccepted = d;
								console.log(self.forumsNotAccepted)
							},
							function(errResponse) {
								console.error('Error while creating notAcceptedForums.');
							});
		};

		function createForum(forum){
			console.log("createForum...")
			ForumService.createForum(forum).then(function(d) {
				self.forum = d;
				/*$scope.cforum = self.forum;
				$rootScope.currentForum = $scope.cforum;*/
				alert("Thank you for creating message")
				$location.path("/index")
			}, function(errResponse) {
				console.error('Error while creating Forum.');
			});
		};
		/*
		function reject(id){
			console.log("reject...")
			var reason = prompt("Please enter the reason");
			ForumService.reject(id, reason).then(function(d) {
				self.forum = d;
				self.fetchAllForums
				$location.path("/manage_Forums")
				alert(self.forum.errorMessage)

			}, null);
		};*/
		function rejectForum(viewForums){
			ForumService.deleteForum(viewForums.forumid).then(function(d) {
				self.deleteForumRequestId = d;		    			
				console.log(self.deleteForumRequestId);
	    			$location.path("/admin")
	    	}, function(errResponse){
	                console.error('Error while deleting BlogRequest');
	            });
	    };

		function updateForum(currentForum){
			console.log("updateForum...")
			ForumService.updateForum(currentForum).then(
					self.fetchAllForums, null);
		};

		function update() {
			{
				console.log('Update the Forum details',
						$rootScope.currentForum);
				updateForum($rootScope.currentForum);
			}
			reset();
		};
		function accept(viewForums) {
			{
				console.log('accept the Forum details')
					
				ForumService.accept(viewForums);
				console.log(viewForums)
				$location.path("/admin")
			}
			
		};
		function get(forum){
			
				$scope.fv=forum;
				
				console.log($scope.fv);
			
				$rootScope.viewForumm=$scope.fv;
				$location.path("/viewForum");
		};
		function adminGet(forums){
			$scope.fvv=forums;
			console.log($scope.fvv);
			$rootScope.viewForums=$scope.fvv;
			$location.path("/adminForum");
		}
		 function submit() {
				{
					console.log('Saving New Forum', self.forum);
					createForum(self.forum);
				}
				reset();
			};

			 function reset() {
				self.forum = {forumid:'null',forumname : '',forumcontent : '',userid:'',username:'',errorCode : '',
						errorMessage : ''};
 };
			 } ]);