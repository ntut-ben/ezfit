function calculate() {
	var parent = document.getElementById("result");
	var child = document.createElement("div");

	var gender = document.getElementById('gender').value;
	var weight = document.getElementById('weight').value;
	var height = document.getElementById('height').value;
	var age = document.getElementById('age').value;
	var exercise = document.getElementById('exercise').value;

	if (gender == 'boy') {
		var bmr = (13.7 * weight) + (5 * height / 100) - (6.8 * age) + 66;
	} else {
		var bmr = (9.6 * weight) + (1.8 * height / 100) - (4.7 * age) + 655;
	}
	;

	var tdd = Math.round(bmr * exercise);

	child.setAttribute("id", "newDiv1");
	child.innerHTML = "維持體重所需熱量" + tdd;
//	child2.setAttribute("id", "newDiv2");
//	child2.innerHTML = "增加肌肉所需熱量" + tdd + 300;
//	child3.setAttribute("id", "newDiv3");
//	child3.innerHTML = "減少脂肪所需熱量" + tdd - 300;
//	parent.appendChild(child);
//	child.appendChild(child2);
//	child2.appendChild(child3);

	//    
	// document.getElementById('Member_BMI').value = bmi;

	return tdd;
}

$('.carousel').carousel({
	interval : 2000
});

$(document).ready(function() {
	$('.grocery-card').hover(function() {
		$(this).find('.grocery-card-text').slideToggle();

	});

});
