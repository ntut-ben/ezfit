$(document).ready(function() {
	$(".collapse").collapse;

	$(".btn-link").click(function(e) {
		e.preventDefault();
		console.log($(this).data("target"));

		// $($(this).data("target")).removeClass("show");

		// $($(this).data("target")).toggle(
		// function remove(params) {
		// $($(this).data("target")).removeClass("show");
		// },
		// function add(params) {
		// $($(this).data("target")).addClass("show");
		// }
		// );
	});
});
