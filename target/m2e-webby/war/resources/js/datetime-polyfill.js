// HTML5 DateTime polyfill | Jonathan Stipe | https://github.com/jonstipe/datetime-polyfill

(function() {
	(function($) {
		$.fn.inputDateTime = function() {
			var decrement, decrementDate, increment, incrementDate, makeDateDisplayString, makeDateTimeString, makeTimeDisplayString, readDateTime, stepNormalize;
			readDateTime = function(dt_str) {
				var dateObj, dayPart, hourPart, matchData, millisecondPart, minutePart, monthPart, secondPart, yearPart;
				if (/^\d{4,}-\d\d-\d\d \d\d:\d\d(?:\:\d\d(?:\.\d+)?)?$/
						.test(dt_str)) {
					matchData = /^(\d+)-(\d+)-(\d+) (\d+):(\d+)(?:\:(\d+)(?:\.(\d+))?)?$/
							.exec(dt_str);
					yearPart = parseInt(matchData[1], 10);
					monthPart = parseInt(matchData[2], 10);
					dayPart = parseInt(matchData[3], 10);
					hourPart = parseInt(matchData[4], 10);
					minutePart = parseInt(matchData[5], 10);
					secondPart = matchData[6] != null ? parseInt(matchData[6],
							10) : 0;
					millisecondPart = matchData[7] != null ? matchData[7] : '0';
					while (millisecondPart.length < 3) {
						millisecondPart += '0';
					}
					if (millisecondPart.length > 3) {
						millisecondPart = millisecondPart.substring(0, 3);
					}
					millisecondPart = parseInt(millisecondPart, 10);
					dateObj = new Date();
					dateObj.setUTCFullYear(yearPart);
					dateObj.setUTCMonth(monthPart - 1);
					dateObj.setUTCDate(dayPart);
					dateObj.setUTCHours(hourPart);
					dateObj.setUTCMinutes(minutePart);
					dateObj.setUTCSeconds(secondPart);
					dateObj.setUTCMilliseconds(millisecondPart);
					return dateObj;
				} else {
					throw "Invalid datetime string: " + dt_str;
				}
			};
			makeDateTimeString = function(date_obj) {
				var dt_arr;
				dt_arr = [ date_obj.getUTCFullYear().toString() ];
				dt_arr.push('-');
				if (date_obj.getUTCMonth() < 9) {
					dt_arr.push('0');
				}
				dt_arr.push((date_obj.getUTCMonth() + 1).toString());
				dt_arr.push('-');
				if (date_obj.getUTCDate() < 10) {
					dt_arr.push('0');
				}
				dt_arr.push(date_obj.getUTCDate().toString());
				dt_arr.push(' ');
				if (date_obj.getUTCHours() < 10) {
					dt_arr.push('0');
				}
				dt_arr.push(date_obj.getUTCHours().toString());
				dt_arr.push(':');
				if (date_obj.getUTCMinutes() < 10) {
					dt_arr.push('0');
				}
				dt_arr.push(date_obj.getUTCMinutes().toString());
				if (date_obj.getUTCSeconds() > 0
						|| date_obj.getUTCMilliseconds() > 0) {
					dt_arr.push(':');
					if (date_obj.getUTCSeconds() < 10) {
						dt_arr.push('0');
					}
					dt_arr.push(date_obj.getUTCSeconds().toString());
					if (date_obj.getUTCMilliseconds() > 0) {
						dt_arr.push('.');
						if (date_obj.getUTCMilliseconds() < 100) {
							dt_arr.push('0');
						}
						if (date_obj.getUTCMilliseconds() < 10) {
							dt_arr.push('0');
						}
						dt_arr.push(date_obj.getUTCMilliseconds().toString());
					}
				}
				//dt_arr.push('Z');
				return dt_arr.join('');
			};
			makeDateDisplayString = function(date_obj, elem) {
				var $elem, date_arr, day_names, month_names;
				$elem = $(elem);
				day_names = $elem.datepicker("option", "dayNames");
				month_names = $elem.datepicker("option", "monthNames");
				date_arr = [ day_names[date_obj.getUTCDay()] ];
				date_arr.push(', ');
				date_arr.push(month_names[date_obj.getUTCMonth()]);
				date_arr.push(' ');
				date_arr.push(date_obj.getUTCDate().toString());
				date_arr.push(', ');
				date_arr.push(date_obj.getUTCFullYear().toString());
				
				return date_arr.join('');
			};
			makeTimeDisplayString = function(date_obj) {
				var ampm, time_arr;
				time_arr = new Array();
				if (date_obj.getUTCHours() === 0) {
					time_arr.push('12');
					ampm = 'AM';
				} else if (date_obj.getUTCHours() > 0
						&& date_obj.getUTCHours() < 10) {
					time_arr.push('0');
					time_arr.push(date_obj.getUTCHours().toString());
					ampm = 'AM';
				} else if (date_obj.getUTCHours() >= 10
						&& date_obj.getUTCHours() < 12) {
					time_arr.push(date_obj.getUTCHours().toString());
					ampm = 'AM';
				} else if (date_obj.getUTCHours() === 12) {
					time_arr.push('12');
					ampm = 'PM';
				} else if (date_obj.getUTCHours() > 12
						&& date_obj.getUTCHours() < 22) {
					time_arr.push('0');
					time_arr.push((date_obj.getUTCHours() - 12).toString());
					ampm = 'PM';
				} else if (date_obj.getUTCHours() >= 22) {
					time_arr.push((date_obj.getUTCHours() - 12).toString());
					ampm = 'PM';
				}
				time_arr.push(':');
				if (date_obj.getUTCMinutes() < 10) {
					time_arr.push('0');
				}
				time_arr.push(date_obj.getUTCMinutes().toString());
				time_arr.push(':');
				if (date_obj.getUTCSeconds() < 10) {
					time_arr.push('0');
				}
				time_arr.push(date_obj.getUTCSeconds().toString());
				if (date_obj.getUTCMilliseconds() > 0) {
					time_arr.push('.');
					if (date_obj.getUTCMilliseconds() % 100 === 0) {
						time_arr.push((date_obj.getUTCMilliseconds() / 100)
								.toString());
					} else if (date_obj.getUTCMilliseconds() % 10 === 0) {
						time_arr.push('0');
						time_arr.push((date_obj.getUTCMilliseconds() / 10)
								.toString());
					} else {
						if (date_obj.getUTCMilliseconds() < 100) {
							time_arr.push('0');
						}
						if (date_obj.getUTCMilliseconds() < 10) {
							time_arr.push('0');
						}
						time_arr.push(date_obj.getUTCMilliseconds().toString());
					}
				}
				time_arr.push(' ');
				time_arr.push(ampm);
				return time_arr.join('');
			};
			increment = function(hiddenField, dateBtn, timeField, calendarDiv) {
				var $hiddenField, max, step, value;
				$hiddenField = $(hiddenField);
				value = readDateTime($hiddenField.val());
				step = $hiddenField.data("step");
				max = $hiddenField.data("max");
				if (!(step != null) || step === 'any') {
					value.setUTCSeconds(value.getUTCSeconds() + 1);
				} else {
					value.setUTCSeconds(value.getUTCSeconds() + step);
				}
				if ((max != null) && value > max) {
					value.setTime(max.getTime());
				}
				$hiddenField.val(makeDateTimeString(value)).change();
				$(dateBtn).text(makeDateDisplayString(value, calendarDiv));
				$(timeField).val(makeTimeDisplayString(value));
				$(calendarDiv).datepicker("setDate", value);
				return null;
			};
			decrement = function(hiddenField, dateBtn, timeField, calendarDiv) {
				var $hiddenField, min, step, value;
				$hiddenField = $(hiddenField);
				value = readDateTime($hiddenField.val());
				step = $hiddenField.data("step");
				min = $hiddenField.data("min");
				if (!(step != null) || step === 'any') {
					value.setUTCSeconds(value.getUTCSeconds() - 1);
				} else {
					value.setUTCSeconds(value.getUTCSeconds() - step);
				}
				if ((min != null) && value < min) {
					value.setTime(min.getTime());
				}
				$hiddenField.val(makeDateTimeString(value)).change();
				$(dateBtn).text(makeDateDisplayString(value, calendarDiv));
				$(timeField).val(makeTimeDisplayString(value));
				$(calendarDiv).datepicker("setDate", value);
				return null;
			};
			incrementDate = function(hiddenField, dateBtn, timeField,
					calendarDiv) {
				var $hiddenField, max, step, value;
				$hiddenField = $(hiddenField);
				value = readDateTime($hiddenField.val());
				step = $hiddenField.data("step");
				max = $hiddenField.data("max");
				value.setUTCDate(value.getUTCDate() + 1);
				if ((max != null) && value > max) {
					value.setTime(max.getTime());
				}
				$hiddenField.val(makeDateTimeString(value)).change();
				$(dateBtn).text(makeDateDisplayString(value, calendarDiv));
				$(timeField).val(makeTimeDisplayString(value));
				$(calendarDiv).datepicker("setDate", value);
				return null;
			};
			decrementDate = function(hiddenField, dateBtn, timeField,
					calendarDiv) {
				var $hiddenField, min, step, value;
				$hiddenField = $(hiddenField);
				value = readDateTime($hiddenField.val());
				step = $hiddenField.data("step");
				min = $hiddenField.data("min");
				value.setUTCDate(value.getUTCDate() - 1);
				if ((min != null) && value < min) {
					value.setTime(min.getTime());
				}
				$hiddenField.val(makeDateTimeString(value)).change();
				$(dateBtn).text(makeDateDisplayString(value, calendarDiv));
				$(timeField).val(makeTimeDisplayString(value));
				$(calendarDiv).datepicker("setDate", value);
				return null;
			};
			stepNormalize = function(inDate, hiddenField) {
				var $hiddenField, kNum, max, min, minNum, raisedStep, step, stepDiff, stepDiff2;
				$hiddenField = $(hiddenField);
				step = $hiddenField.data("step");
				min = $hiddenField.data("min");
				max = $hiddenField.data("max");
				if ((step != null) && step !== 'any') {
					kNum = inDate.getTime();
					raisedStep = step * 1000;
					if (min == null) {
						min = new Date(0);
					}
					minNum = min.getTime();
					stepDiff = (kNum - minNum) % raisedStep;
					stepDiff2 = raisedStep - stepDiff;
					if (stepDiff === 0) {
						return inDate;
					} else {
						if (stepDiff > stepDiff2) {
							return new Date(inDate.getTime() + stepDiff2);
						} else {
							return new Date(inDate.getTime() - stepDiff);
						}
					}
				} else {
					return inDate;
				}
			};
			$(this)
					.filter('input[type="datetime"]')
					.each(
							function() {
								var $calendarContainer, $calendarDiv, $dateBtn, $hiddenField, $this, $timeField, btnContainer, calendarContainer, calendarDiv, className, closeFunc, dateBtn, downBtn, halfHeight, hiddenField, max, min, step, style, timeField, upBtn, value;
								$this = $(this);
								value = $this.attr('value');
								min = $this.attr('min');
								max = $this.attr('max');
								step = $this.attr('step');
								className = $this.attr('class');
								style = $this.attr('style');
								if ((value != null)
										&& /^\d{4,}-\d\d-\d\d \d\d:\d\d?$/
												.test(value)) {
									value = readDateTime(value);
								} else {
									value = new Date();
								}
								if (min != null) {
									min = readDateTime(min);
									if (value < min) {
										value.setTime(min.getTime());
									}
								}
								if (max != null) {
									max = readDateTime(max);
									if (value > max) {
										value.setTime(max.getTime());
									}
								}
								if ((step != null) && step !== 'any') {
									step = parseFloat(step);
								}
								hiddenField = document.createElement('input');
								$hiddenField = $(hiddenField);
								$hiddenField.attr({
									type : "hidden",
									name : $this.attr('name'),
									value : makeDateTimeString(value)
								});
								$hiddenField.data({
									min : min,
									max : max,
									step : step
								});
								value = stepNormalize(value, hiddenField);
								$hiddenField.attr('value',
										makeDateTimeString(value));
								calendarContainer = document
										.createElement('span');
								$calendarContainer = $(calendarContainer);
								if (className != null) {
									$calendarContainer.attr('class', className);
								}
								if (style != null) {
									$calendarContainer.attr('style', style);
								}
								calendarDiv = document.createElement('div');
								$calendarDiv = $(calendarDiv);
								$calendarDiv.css({
									display : 'none',
									position : 'absolute'
								});
								dateBtn = document.createElement('button');
								$dateBtn = $(dateBtn);
								$dateBtn.addClass('datetime-datepicker-button');
								timeField = document.createElement('input');
								$timeField = $(timeField);
								$timeField.attr({
									type : "text",
									value : makeTimeDisplayString(value),
									size : 14
								});
								$this.replaceWith(hiddenField);
								$dateBtn.appendTo(calendarContainer);
								$calendarDiv.appendTo(calendarContainer);
								$calendarContainer.insertAfter(hiddenField);
								$timeField.insertAfter(calendarContainer);
								halfHeight = ($timeField.outerHeight() / 2)
										+ 'px';
								upBtn = document.createElement('div');
								$(upBtn)
										.addClass(
												'datetime-spin-btn datetime-spin-btn-up')
										.css('height', halfHeight);
								downBtn = document.createElement('div');
								$(downBtn)
										.addClass(
												'datetime-spin-btn datetime-spin-btn-down')
										.css('height', halfHeight);
								btnContainer = document.createElement('div');
								btnContainer.appendChild(upBtn);
								btnContainer.appendChild(downBtn);
								$(btnContainer).addClass(
										'datetime-spin-btn-container')
										.insertAfter(timeField);
								$calendarDiv.datepicker({
									dateFormat : 'MM dd, yy',
									showButtonPanel : true,
									monthNames: ['Leden', 'Únor', 'Březen', 'Duben', 'Květen', 'Červen', 'Červenec', 'Srpen', 'Září', 'Říjen', 'Listopad', 'Prosinec'],
									dayNames: ['Neděle', 'Pondělí', 'Úterý', 'Středa', 'Čtvrtek', 'Pátek', 'Sobota'],
									dayNamesMin: [ 'Ne', 'Po', 'Út', 'St', 'Čt', 'Pá', 'So'],
									currentText: 'Dnes',
									nextText: 'Další',
									prevText: 'Předchozí'
								
								});
								$dateBtn.text(makeDateDisplayString(value,
										calendarDiv));
								if (min != null) {
									$calendarDiv.datepicker("option",
											"minDate", min);
								}
								if (max != null) {
									$calendarDiv.datepicker("option",
											"maxDate", max);
								}
								if (Modernizr.csstransitions) {
									calendarDiv.className = "datetime-calendar-dialog datetime-closed";
									$dateBtn
											.click(function(event) {
												$calendarDiv
														.off('transitionend oTransitionEnd webkitTransitionEnd MSTransitionEnd');
												calendarDiv.style.display = 'block';
												calendarDiv.className = "datetime-calendar-dialog datetime-open";
												event.preventDefault();
												return false;
											});
									closeFunc = function(event) {
										var transitionend_function;
										if (calendarDiv.className === "datetime-calendar-dialog datetime-open") {
											transitionend_function = function(
													event) {
												calendarDiv.style.display = 'none';
												$calendarDiv
														.off(
																"transitionend oTransitionEnd webkitTransitionEnd MSTransitionEnd",
																transitionend_function);
												return null;
											};
											$calendarDiv
													.on(
															"transitionend oTransitionEnd webkitTransitionEnd MSTransitionEnd",
															transitionend_function);
											calendarDiv.className = "datetime-calendar-dialog datetime-closed";
										}
										if (event != null) {
											event.preventDefault();
										}
										return null;
									};
								} else {
									$dateBtn.click(function(event) {
										$calendarDiv.fadeIn('fast');
										event.preventDefault();
										return false;
									});
									closeFunc = function(event) {
										$calendarDiv.fadeOut('fast');
										if (event != null) {
											event.preventDefault();
										}
										return null;
									};
								}
								$calendarDiv.mouseleave(closeFunc);
								$calendarDiv
										.datepicker(
												"option",
												"onSelect",
												function(dateText, inst) {
													var dateObj, origDate;
													origDate = readDateTime($hiddenField
															.val());
													dateObj = $.datepicker
															.parseDate(
																	'MM dd, yy',
																	dateText);
													dateObj
															.setUTCHours(origDate
																	.getUTCHours());
													dateObj
															.setUTCMinutes(origDate
																	.getUTCMinutes());
													dateObj
															.setUTCSeconds(origDate
																	.getUTCSeconds());
													dateObj
															.setUTCMilliseconds(origDate
																	.getUTCMilliseconds());
													if ((min != null)
															&& dateObj < min) {
														dateObj.setTime(min
																.getTime());
													} else if ((max != null)
															&& dateObj > max) {
														dateObj.setTime(max
																.getTime());
													}
													dateObj = stepNormalize(
															dateObj,
															hiddenField);
													$hiddenField
															.val(
																	makeDateTimeString(dateObj))
															.change();
													$timeField
															.val(makeTimeDisplayString(dateObj));
													$dateBtn
															.text(makeDateDisplayString(
																	dateObj,
																	calendarDiv));
													closeFunc();
													return null;
												});
								$calendarDiv.datepicker("setDate", value);
								$dateBtn
										.on({
											DOMMouseScroll : function(event) {
												if (event.originalEvent.detail < 0) {
													incrementDate(hiddenField,
															dateBtn, timeField,
															calendarDiv);
												} else {
													decrementDate(hiddenField,
															dateBtn, timeField,
															calendarDiv);
												}
												event.preventDefault();
												return null;
											},
											mousewheel : function(event) {
												if (event.originalEvent.wheelDelta > 0) {
													incrementDate(hiddenField,
															dateBtn, timeField,
															calendarDiv);
												} else {
													decrementDate(hiddenField,
															dateBtn, timeField,
															calendarDiv);
												}
												event.preventDefault();
												return null;
											},
											keypress : function(event) {
												if (event.keyCode === 38) {
													incrementDate(hiddenField,
															dateBtn, timeField,
															calendarDiv);
													event.preventDefault();
												} else if (event.keyCode === 40) {
													decrementDate(hiddenField,
															dateBtn, timeField,
															calendarDiv);
													event.preventDefault();
												}
												return null;
											}
										});
								$timeField
										.on({
											DOMMouseScroll : function(event) {
												if (event.originalEvent.detail < 0) {
													increment(hiddenField,
															dateBtn, timeField,
															calendarDiv);
												} else {
													decrement(hiddenField,
															dateBtn, timeField,
															calendarDiv);
												}
												event.preventDefault();
												return null;
											},
											mousewheel : function(event) {
												if (event.originalEvent.wheelDelta > 0) {
													increment(hiddenField,
															dateBtn, timeField,
															calendarDiv);
												} else {
													decrement(hiddenField,
															dateBtn, timeField,
															calendarDiv);
												}
												event.preventDefault();
												return null;
											},
											keypress : function(event) {
												var _ref, _ref1;
												if (event.keyCode === 38) {
													increment(hiddenField,
															dateBtn, timeField,
															calendarDiv);
													event.preventDefault();
												} else if (event.keyCode === 40) {
													decrement(hiddenField,
															dateBtn, timeField,
															calendarDiv);
													event.preventDefault();
												} else if (((_ref = event.keyCode) !== 35
														&& _ref !== 36
														&& _ref !== 37
														&& _ref !== 39 && _ref !== 46)
														&& ((_ref1 = event.which) !== 8
																&& _ref1 !== 9
																&& _ref1 !== 32
																&& _ref1 !== 45
																&& _ref1 !== 46
																&& _ref1 !== 47
																&& _ref1 !== 48
																&& _ref1 !== 49
																&& _ref1 !== 50
																&& _ref1 !== 51
																&& _ref1 !== 52
																&& _ref1 !== 53
																&& _ref1 !== 54
																&& _ref1 !== 55
																&& _ref1 !== 56
																&& _ref1 !== 57
																&& _ref1 !== 58
																&& _ref1 !== 65
																&& _ref1 !== 77
																&& _ref1 !== 80
																&& _ref1 !== 97
																&& _ref1 !== 109 && _ref1 !== 112)) {
													event.preventDefault();
												}
												return null;
											},
											change : function(event) {
												var ampm, dateObj, hours, matchData, milliseconds, minutes, seconds;
												$this = $(this);
												if (/^((?:1[0-2])|(?:0[1-9]))\:[0-5]\d(?:\:[0-5]\d(?:\.\d+)?)?\s[AaPp][Mm]$/
														.test($this.val())) {
													matchData = /^(\d\d):(\d\d)(?:\:(\d\d)(?:\.(\d+))?)?\s([AaPp][Mm])$/
															.exec($this.val());
													hours = parseInt(
															matchData[1], 10);
													minutes = parseInt(
															matchData[2], 10);
													seconds = parseInt(
															matchData[3], 10) || 0;
													milliseconds = matchData[4];
													if (milliseconds == null) {
														milliseconds = 0;
													} else if (milliseconds.length > 3) {
														milliseconds = parseInt(
																milliseconds
																		.substring(
																				0,
																				3),
																10);
													} else if (milliseconds.length < 3) {
														while (milliseconds.length < 3) {
															milliseconds += '0';
														}
														milliseconds = parseInt(
																milliseconds,
																10);
													} else {
														milliseconds = parseInt(
																milliseconds,
																10);
													}
													ampm = matchData[5]
															.toUpperCase();
													dateObj = readDateTime($hiddenField
															.val());
													if (ampm === 'AM'
															&& hours === 12) {
														hours = 0;
													} else if (ampm === 'PM'
															&& hours !== 12) {
														hours += 12;
													}
													dateObj.setUTCHours(hours);
													dateObj
															.setUTCMinutes(minutes);
													dateObj
															.setUTCSeconds(seconds);
													dateObj
															.setUTCMilliseconds(milliseconds);
													if ((min != null)
															&& dateObj < min) {
														$hiddenField
																.val(
																		makeDateTimeString(min))
																.change();
														$this
																.val(makeTimeDisplayString(min));
													} else if ((max != null)
															&& dateObj > max) {
														$hiddenField
																.val(
																		makeDateTimeString(max))
																.change();
														$this
																.val(makeTimeDisplayString(max));
													} else {
														dateObj = stepNormalize(
																dateObj,
																hiddenField);
														$hiddenField
																.val(
																		makeDateTimeString(dateObj))
																.change();
														$this
																.val(makeTimeDisplayString(dateObj));
													}
												} else {
													$this
															.val(makeTimeDisplayString(readDateTime($hiddenField
																	.val())));
												}
												return null;
											}
										});
								$(upBtn)
										.on(
												"mousedown",
												function(event) {
													var releaseFunc, timeoutFunc;
													increment(hiddenField,
															dateBtn, timeField,
															calendarDiv);
													timeoutFunc = function(
															hiddenField,
															dateBtn, timeField,
															calendarDiv,
															incFunc) {
														incFunc(hiddenField,
																dateBtn,
																timeField,
																calendarDiv);
														$(timeField)
																.data(
																		'timeoutID',
																		window
																				.setTimeout(
																						timeoutFunc,
																						10,
																						hiddenField,
																						dateBtn,
																						timeField,
																						calendarDiv,
																						incFunc));
														return null;
													};
													releaseFunc = function(
															event) {
														window
																.clearTimeout($(
																		timeField)
																		.data(
																				'timeoutID'));
														$(document).off(
																'mouseup',
																releaseFunc);
														$(upBtn).off(
																'mouseleave',
																releaseFunc);
														return null;
													};
													$(document).on('mouseup',
															releaseFunc);
													$(upBtn).on('mouseleave',
															releaseFunc);
													$(timeField)
															.data(
																	'timeoutID',
																	window
																			.setTimeout(
																					timeoutFunc,
																					700,
																					hiddenField,
																					dateBtn,
																					timeField,
																					calendarDiv,
																					increment));
													return null;
												});
								$(downBtn)
										.on(
												"mousedown",
												function(event) {
													var releaseFunc, timeoutFunc;
													decrement(hiddenField,
															dateBtn, timeField,
															calendarDiv);
													timeoutFunc = function(
															hiddenField,
															dateBtn, timeField,
															calendarDiv,
															decFunc) {
														decFunc(hiddenField,
																dateBtn,
																timeField,
																calendarDiv);
														$(timeField)
																.data(
																		'timeoutID',
																		window
																				.setTimeout(
																						timeoutFunc,
																						10,
																						hiddenField,
																						dateBtn,
																						timeField,
																						calendarDiv,
																						decFunc));
														return null;
													};
													releaseFunc = function(
															event) {
														window
																.clearTimeout($(
																		timeField)
																		.data(
																				'timeoutID'));
														$(document).off(
																'mouseup',
																releaseFunc);
														$(downBtn).off(
																'mouseleave',
																releaseFunc);
														return null;
													};
													$(document).on('mouseup',
															releaseFunc);
													$(downBtn).on('mouseleave',
															releaseFunc);
													$(timeField)
															.data(
																	'timeoutID',
																	window
																			.setTimeout(
																					timeoutFunc,
																					700,
																					hiddenField,
																					dateBtn,
																					timeField,
																					calendarDiv,
																					decrement));
													return null;
												});
								return null;
							});
			return this;
		};
		$(function() {
			if (!Modernizr.inputtypes.datetime) {
				$('input[type="datetime"]').inputDateTime();
			}
			return null;
		});
		return null;
	})(jQuery);
}).call(this);