'use strict';

app
		.controller(
				'JobController',
				[
						'JobService',
						'$location',
						'$rootScope',
						'$scope',
						function(JobService, $location, $rootScope, $scope) {
							console.log("JobController...")
							var self = this;

							self.job = {
								jobid : '',
								title : '',
								companyname : '',
								email : '',
								postdate : '',
								qualification : '',
								status : '',
								errorCode : '',
								errorMessage : ''
							};
							this.jobs = [];

							self.appjob = {
								id : '',
								jobid : '',
								title : '',
								companyname : '',
								email : '',
								userid : '',
								username : '',
								qualification : '',
								status : '',
								timeStamp : ''
							};

							this.appjobs = [];

							self.get = get;

							self.applyJobs = function(job) {
								console.log("AllapplyJobs...")
								self.appjob.jobid = job.jobid;
								console.log(self.appjob.jobid)
								self.appjob.companyname = job.title;
								self.appjob.userid = $rootScope.currentUser.userid;
								self.appjob.username = $rootScope.currentUser.username;
								JobService
										.applyjobs(self.appjob)
										.then(
												function(d) {

													console.log("working")
												},
												function(errResponse) {
													console
															.error('Error while applying Jobs');
												});
							};

							self.BringAllAppJobs = function() {
								console.log("Applied Jobs List...")
								JobService
										.BringAllAppJobs()
										.then(
												function(d) {
													self.appjobs = d;
													console.log(self.jobs)
												},
												function(errResponse) {
													console
															.error('Error while fetching  applied Jobs');
												});
							};

							self.getMyAppliedJobs = function() {
								console
										.log('calling the method getMyAppliedJobs');
								JobService.getMyAppliedJobs().then(function(d) {
									self.jobs = d;

									$location.path("/view_applied_jobs")
								},

								function(errResponse) {
									console.error('Error while fetching Jobs');
								});
							};

							self.rejectJobApplication = function(userid) {
								var jobid = $rootScope.selectedJob.id;
								JobService
										.rejectJobApplication(userid, jobid)
										.then(
												function(d) {
													self.job = d;
													alert("You have successfully rejected the job application of the "
															+ "user : "
															+ userid)
												},
												function(errResponse) {
													console
															.error('Error while rejecting Job application.');
												});
							};

							self.getAllJobs = function() {
								console.log('calling the method getAllJobs');
								JobService
										.getAllJobs()
										.then(
												function(d) {
													self.jobs = d;
												},
												function(errResponse) {
													console
															.error('Error while fetching All opend jobs');
												});
							};

							self.getAllJobs();

							self.submit = function() {
								{
									console.log('submit a new job', self.job);
									self.postAJob(self.job);
								}
								self.reset();
							};

							self.postAJob = function(job) {
								console.log('submit a new job', self.job);
								JobService.postAJob(job).then(function(d) {
									alert("You successfully posted the job")
								}, function(errResponse) {
									console.error('Error while posting job.');
								});
							};

							self.getJobDetails = function(jobid) {
								console.log('get Job details of the id', jobid);
								JobService
										.getJobDetails(jobid)
										.then(
												function(d) {
													self.job = d;

													$location
															.path('/view_job_details');
												},
												function(errResponse) {
													console
															.error('Error while fetching blog details');
												});
							};
							function get(job) {
								$scope.jv = job;
								console.log($scope.jv);
								$rootScope.viewJob = $scope.jv;
								console.log('viewJob')
								$location.path("/view_job_details");

							}
							;
							self.reset = function() {
								console.log('resetting the Job');
								self.job = {
									jobid : '',
									title : '',
									companyname : '',
									email : '',
									postdate : '',
									qualification : '',
									status : '',

									errorCode : '',
									errorMessage : ''
								};
								// $scope.myForm.$setPristine(); // reset Form
							};

						} ]);