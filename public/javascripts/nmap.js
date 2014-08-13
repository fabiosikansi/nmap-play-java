$(function() {
	function getRectCenter (rect) {
		var initX = parseFloat(rect.attr("x"));
		var initY = parseFloat(rect.attr("y"));
		var initW = parseFloat(rect.attr("width"));
		var initH = parseFloat(rect.attr("height"));
		return {x: parseFloat(initX + (initW/2)),y: parseFloat(initY + (initH/2))};
	}
	function drawArrow(start,end) {
		d3.selectAll(".drawPanel .arrow").remove();
		var line = d3.selectAll(".drawPanel").append('line')
					.attr("class","arrow")
					.attr('x2',start.x)
					.attr('y2',start.y)
					.attr('x1',end.x)
					.attr('y1',end.y)
					.style('stroke','black')
					.style('stroke-width','3px')
					.style('marker-end','url(#arrow)');
	}
	
	$("#caseSelectionForm").on("submit",function (e) {
		e.preventDefault();
		$("#caseSelectionFormButton").html("Loading...");
		var scrW = $("#drawArea").outerWidth() - 10;
		var scrH = $("#drawArea").outerHeight() - 60;
		d3.json("/loadNMap/" + $("#caseSelectionFormSelect").val() + "/" + scrW + "/" + scrH, function (error, json) {
			d3.select("#drawArea svg").remove();
			var svg = d3.select("#drawArea").append("svg").attr("width",scrW).attr("height",scrH);
			
			svg.append('svg:defs').append('svg:marker')
			        .attr('id', 'arrow')
			        .attr('viewBox', '0 -5 10 10')
			        .attr('refX', 6)
			        .attr('markerWidth', 3)
			        .attr('markerHeight', 3)
			        .attr('orient', 'auto')
			        .append('svg:path')
			        .attr('d', 'M0,-5L10,0L0,5')
			        .attr('fill', '#000');
			
			var g = svg.append("g").attr("class","drawPanel");
			var rect = g.selectAll('rect').data(json.boxes).enter()
						.append("rect")
						.attr("data-item", function (d,i) { return i; })
						.attr("x", function (d) { return d.x; })
						.attr("y", function (d) { return d.y; })
						.attr("width", function (d) { return d.w; })
						.attr("height", function (d) { return d.h; })
						.style("stroke", "black")
						.style("stroke-width", "2")
						.style("opacity", "0.2")
						.style("fill", function (d) {
							if (d.klass == 1) {
								return "#1b9e77";
							} else if (d.klass == 0) {
								return "#d95f02";
							} else if (d.klass == 2) {
								return "#7570b3";
							} else if (d.klass == 3) {
								return "#e6ab02";
							} else if (d.klass == 4) {
								return "#e7298a";
							}
						})
						.on('mouseover', function () {
							d3.select(this).style('fill','#333');
							d3.select("#elem-point-" + d3.select(this).attr('data-item')).attr("r", 5);
							var c = getRectCenter(d3.select(this)),
								elemX = d3.select("#elem-point-" + d3.select(this).attr('data-item')).attr("cx")
								elemY = d3.select("#elem-point-" + d3.select(this).attr('data-item')).attr("cy");
							drawArrow(c,{x: elemX,y: elemY});
						})
						.on('mouseout', function () {
							d3.select("#elem-point-" + d3.select(this).attr('data-item')).attr("r", 3);
							d3.select(this).style("fill", function (d) {
								if (d.klass == 1) {
									return "#1b9e77";
								} else if (d.klass == 0) {
									return "#d95f02";
								} else if (d.klass == 2) {
									return "#7570b3";
								} else if (d.klass == 3) {
									return "#e6ab02";
								} else if (d.klass == 4) {
									return "#e7298a";
								}
							});
						});
			
			var circle = g.selectAll('circle').data(json.boxes).enter()
						.append("circle")
						.attr("data-item", function (d,i) { return i; })
						.attr("id", function (d,i) { return ("elem-point-" + i); })
						.attr("cx", function (d) { return d.elemx; })
						.attr("cy", function (d) { return d.elemy; })
						.attr("r", 3)
						.style("stroke", "none")
						.style("fill", function (d) {
							if (d.klass == 1) {
								return "#1b9e77";
							} else if (d.klass == 0) {
								return "#d95f02";
							} else if (d.klass == 2) {
								return "#7570b3";
							} else if (d.klass == 3) {
								return "#e6ab02";
							} else if (d.klass == 4) {
								return "#e7298a";
							}
						});
			$("#caseSelectionFormButton").html("Load");
			
		});
	});
	$("#caseSelectionForm").submit();
})