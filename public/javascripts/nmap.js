$(function() {
	
	$("#caseSelectionForm").on("submit",function (e) {
		e.preventDefault();
		var scrW = $("#drawArea").outerWidth() - 10;
		var scrH = $("#drawArea").outerHeight() - 60;
		console.log("[" + scrW + "," + scrH + "]");
		d3.json("/loadNMap/" + $("#caseSelectionFormSelect").val() + "/" + scrW + "/" + scrH, function (error, json) {
			d3.select("#drawArea svg").remove();
			var svg = d3.select("#drawArea").append("svg").attr("width",scrW).attr("height",scrH);
			var g = svg.append("g");
			var rect = g.selectAll('rect').data(json.boxes).enter()
						.append("rect")
						.attr("x", function (d) { return d.x; })
						.attr("y", function (d) { return d.y; })
						.attr("width", function (d) { return d.w; })
						.attr("height", function (d) { return d.h; })
						.style("stroke", "black")
						.style("fill", "none");
			
			var circle = g.selectAll('circle').data(json.points).enter()
						.append("circle")
						.attr("cx", function (d) { return d.x; })
						.attr("cy", function (d) { return d.y; })
						.attr("r", 3)
						.style("stroke", "none")
						.style("fill", function (d) {
							if (d.klass == 1) {
								return "red";
							} else {
								return "blue";
							}
						});
			
		});
	});
})