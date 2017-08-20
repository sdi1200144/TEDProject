$(document).ready(function() {
    $('.register_form').bootstrapValidator({
        // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: 
        {
            firstname: {
                validators: {
                        stringLength: {
                        min: 2,
                    },
                        notEmpty: {
                        message: 'Please enter your First Name'
                    }
                }
            },
             lastname: {
                validators: {
                     stringLength: {
                        min: 2,
                    },
                    notEmpty: {
                        message: 'Please enter your Last Name'
                    }
                }
            },
			 username: {
                validators: {
                	 stringLength: {
                         min: 6,
                         max: 30,
                         message: 'The username must be more than 6 and less than 30 characters long'
                     },
                     regexp: {
                         regexp: /^[a-zA-Z0-9_\.]+$/,
                         message: 'The username can only consist of alphabetical, number, dot and underscore'
                     },
                    notEmpty: {
                        message: 'Please enter your Username'
                    }
                }
            },
			 password: {
                validators: {
                     stringLength: {
                        min: 8,
                        message: 'The password must be more than 8 characters long'
                    },
                    notEmpty: {
                        message: 'Please enter your Password'
                    },
                    identical: {
                        field: 'confirm_password',
                        message: 'The password and its confirm are not the same'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\.]+$/,
                        message: 'The password can only consist of alphabetical, number, dot and underscore'
                    },
                    
                }
            },
			confirm_password: {
                validators: {
                     stringLength: {
                        min: 8,
                        message: 'The password must be more than 8 characters long'
                    },
                    notEmpty: {
                        message: 'Please confirm your Password'
                    },
                    identical: {
                        field: 'password',
                        message: 'The password and its confirm are not the same'
                    }
                }
            },
            email: {
                validators: {
                    notEmpty: {
                        message: 'Please enter your Email Address'
                    },
                    emailAddress: {
                        message: 'Please enter a valid Email Address'
                    }
                }
            },
            contact_no: {
                validators: {
                  stringLength: {
                        min: 10, 
                        max: 10,
                    notEmpty: {
                        message: 'Please enter your Contact No.'
                     },
                     phone: {
                         message: 'The input is not a valid phone number',
                         country: 'GR'
                     }
                }
            },
			 role: {
                validators: {
                    notEmpty: {
                        message: 'Please select your role'
                    }
                }
            },
            /////////////////////////////////////////
                }
            }
        });
});