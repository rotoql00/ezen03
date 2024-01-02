var surveyJSON = {
			"locale" : "ko",
			"title" : {
				"ko" : "SURVEY"
			},
			"completedHtml" : {
				"default" : "<h3>Thank you for your feedback.</h3> <h5>Your thoughts and ideas will help us to create a great product!</h5>",
				"ko" : "<h3>설문에 참여해 주셔서 감사합니다.</h3> <h5>고객님의 myPage의 쿠폰함에서 발급된 쿠폰을 확인하실수 있습니다.</h5> <br> <a href=\"survey_ok.jsp\">설문 결과 보러가기</a> "
			},
			"completedHtmlOnCondition" : [
					{
						"expression" : "{nps_score} > 8",
						"html" : "<h3>Thank you for your feedback.</h3> <h5>We glad that you love our product. Your ideas and suggestions will help us to make our product even better!</h5>"
					},
					{
						"expression" : "{nps_score} < 7",
						"html" : "<h3>Thank you for your feedback.</h3> <h5> We are glad that you share with us your ideas.We highly value all suggestions from our customers. We do our best to improve the product and reach your expectation.</h5>\n"
					} ],
			"pages" : [
					{
						"name" : "page1",
						"elements" : [ {
							"type" : "radiogroup",
							"name" : "user gender",
							"title" : {
								"ko" : "고객님의 성별을 선택해주세요."
							},
							"isRequired" : true,
							"choices" : [ {
								"value" : "male",
								"text" : {
									"ko" : "남성"
								}
							}, {
								"value" : "female",
								"text" : {
									"ko" : "여성"
								}
							} ]
						} ]
					},
					{
						"name" : "page2",
						"elements" : [ {
							"type" : "checkbox",
							"name" : "shoe to use",
							"title" : {
								"ko" : "신발 활용처를 선택해주세요.(중복선택가능)"
							},
							"isRequired" : true,
							"hasOther" : true,
							"choices" : [ {
								"value" : "use wear",
								"text" : {
									"ko" : "실제 착용"
								}
							}, {
								"value" : "collection",
								"text" : {
									"ko" : "수집"
								}
							}, {
								"value" : "resale",
								"text" : {
									"ko" : "재판매"
								}
							} ],
							"otherText" : {
								"ko" : "기타..."
							}
						} ]
					},
					{
						"name" : "page3",
						"elements" : [ {
							"type" : "checkbox",
							"name" : "favorite brand",
							"title" : {
								"ko" : "좋아하는 브랜드를 선택해주세요.신발 활용처를 선택해주세요.(중복선택가능)"
							},
							"isRequired" : true,
							"hasOther" : true,
							"choices" : [ {
								"value" : "jordan",
								"text" : {
									"ko" : "조던"
								}
							}, {
								"value" : "nike",
								"text" : {
									"ko" : "나이키"
								}
							}, {
								"value" : "adidas",
								"text" : {
									"ko" : "아디다스"
								}
							} ],
							"otherText" : {
								"ko" : "기타..."
							}
						} ]
					},
					{
						"name" : "page4",
						"elements" : [ {
							"type" : "checkbox",
							"name" : "luv shoe model",
							"title" : {
								"ko" : "좋아하는 모델을 선택해주세요.(중복선택가능)"
							},
							"isRequired" : true,
							"hasOther" : true,
							"choices" : [ {
								"value" : "jordan 1",
								"text" : {
									"ko" : "조던 1"
								}
							}, {
								"value" : "jorda 3",
								"text" : {
									"ko" : "조던 3"
								}
							}, {
								"value" : "jordan 4",
								"text" : {
									"ko" : "조던 4"
								}
							}, {
								"value" : "jordan 5",
								"text" : {
									"ko" : "조던 5"
								}
							}, {
								"value" : "jordan 6",
								"text" : {
									"ko" : "조던 6"
								}
							}, {
								"value" : "jordan 11",
								"text" : {
									"ko" : "조던 11"
								}
							}, {
								"value" : "jordan 13",
								"text" : {
									"ko" : "조던 13"
								}
							}, {
								"value" : "air max",
								"text" : {
									"ko" : "에어맥스"
								}
							}, {
								"value" : "air force",
								"text" : {
									"ko" : "에어포스"
								}
							}, {
								"value" : "yeezy 300",
								"text" : {
									"ko" : "이지 300th"
								}
							}, {
								"value" : "yeezy 500",
								"text" : {
									"ko" : "이지 500th"
								}
							}, {
								"value" : "yeezy 700",
								"text" : {
									"ko" : "이지 700th"
								}
							}, {
								"value" : "collaboration",
								"text" : {
									"ko" : "X 콜라보레이션"
								}
							} ],
							"otherText" : {
								"ko" : "기타..."
							}
						} ]
					},
					{
						"name" : "page5",
						"elements" : [ {
							"type" : "checkbox",
							"name" : "shoe info",
							"title" : {
								"ko" : "상품정보를 얻는곳을 선택해주세요.(중복선택가능)"
							},
							"isRequired" : true,
							"hasOther" : true,
							"choices" : [ {
								"value" : "brand site",
								"text" : {
									"ko" : "해당 브랜드 사이트"
								}
							}, {
								"value" : "community",
								"text" : {
									"ko" : "커뮤니티"
								}
							}, {
								"value" : "SNS",
								"text" : {
									"ko" : "SNS"
								}
							} ],
							"otherText" : {
								"ko" : "기타..."
							}
						} ]
					},
					{
						"name" : "page6",
						"elements" : [ {
							"type" : "checkbox",
							"name" : "tie shoe search",
							"title" : {
								"ko" : "TIE SHOE를 찾아온 경로를 선택해주세요.(중복선택가능)"
							},
							"isRequired" : true,
							"hasOther" : true,
							"choices" : [ {
								"value" : "search",
								"text" : {
									"ko" : "검색"
								}
							}, {
								"value" : "recommend",
								"text" : {
									"ko" : "지인 추천"
								}
							}, {
								"value" : "community SNS",
								"text" : {
									"ko" : "커뮤니티, SNS"
								}
							} ],
							"otherText" : {
								"ko" : "기타..."
							}
						} ]
					} ],
			"sendResultOnPageNext" : true,
			"showProgressBar" : "top",
			"pagePrevText" : {
				"ko" : "뒤로 "
			},
			"pageNextText" : {
				"ko" : "다음"
			}
		}

		function sendDataToServer(survey) {
			   console.log (JSON.stringify(survey.data));
			   
			   var surveyResult = {
					   "userForm": JSON.stringify(survey.data)
			   };
			 
			$.ajax({
				url : "./surveyOk",//${pageContext.request.contextPath}
				type : "POST",
				data : surveyResult,
				success : function(data) {
					alert("설문 완료");
					window.location.href = "./";//"${pageContext.request.contextPath}";
				}
			});

		}

		// 설문지 api 로딩
		var survey = new Survey.Model(surveyJSON);
		$("#surveyContainer").Survey({
			model : survey,
			onComplete : sendDataToServer
		});