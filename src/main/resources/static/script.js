const URL = 'http://localhost:8080/api';

//Добавление всех сохраненных в базе заметок
async function getAllNotes(){
	await fetch(URL)
		.then((response) => response.json())
		.then((result) => {
		  addNotesToWindow(result);
		}).catch((error)=>console.log(error))
}
//Добавление всех сохраненных в базе заметок с задержкой
function getAllNotesDelay(){
	setTimeout(function(){getAllNotes()}, 200);
}


var noteForm = $("#note_form")[0]
noteForm.addEventListener('submit', function(event)
{
    event.preventDefault();
	let result = {}
    let formData = new FormData($("#note_form")[0])
	noteForm.reset()
    for (var entry of formData.entries())
    {
        result[entry[0]] = entry[1];
    }
    let jsonResult = [result]
    result = JSON.stringify(result)
    
    fetch(URL,
	{
	    headers: {
	      'Accept': 'application/json',
	      'Content-Type': 'application/json'
	    },
	    method: "POST",
	    body: result
	})
	.then(removeNotesFromWindow(),getAllNotesDelay()).
	then((response) => { 
	if(!response.ok){
    response.json().then((errorJson) => {
        $('.error').append(errorJson.errors[0].defaultMessage)
        clearErrorInfo()
    });}})
	.catch((err) => console.log(err))
});

var filterForm = $("#filter_form")[0]
filterForm.addEventListener('submit', function(event)
{
    event.preventDefault();
	let result = {}
    let formData = new FormData($("#filter_form")[0])
	filterForm.reset()
    for (var entry of formData.entries())
    {
        result[entry[0]] = entry[1];
    }
    result = JSON.stringify(result)
    filterNotes(result)
});

async function filterNotes(filterJson){
	let response = await fetch(URL+'/filter', {
		  method: 'POST',
		  headers: {
			'Accept': 'application/json',
		    'Content-Type': 'application/json;'
		  },
		  body: filterJson
		});
	if(response.ok){
		let result = await response.json()
		removeNotesFromWindow()
		addNotesToWindow(result)
	}
}

function removeNote(noteId){
	fetch(URL+'/'+noteId, {
		  method:'DELETE'
		});
	removeNotesFromWindow()
	getAllNotesDelay()
}
	
function addNotesToWindow(notes){
	let parent = $(".noteblock")
	for(let note of notes){
		if(note.title===''){
			parent.append(
					'<div class="message">'+
					note.text + 
					'<button onclick=removeNote('+note.id+')>x</button>'+
					'</div>'
			)
		}else{
			parent.append(
					'<div class="message">'+
					'<b>'+note.title +'</b>'+
					'<button onclick=removeNote('+note.id+')>x</button>'+
					'</div>'
			)
		}
	}
}

function removeNotesFromWindow(){
	let parent = $(".noteblock")
	parent.empty()
}

function clearErrorInfo()
{
	setTimeout(function(){
		let parent =  $('.error')
		parent.empty()
	}, 4000);
}

getAllNotes()