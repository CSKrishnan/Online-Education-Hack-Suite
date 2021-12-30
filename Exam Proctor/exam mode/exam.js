function notify(name,message,title,position,theme){
	var title = title;
	var message = name+message;
	var position = position;
	var duration = 2000;
	var theme = theme;
	var closeOnClick = true;
	var displayClose = true;

	if(!message) {
		message = 'You did not enter a message...';
	}

	window.createNotification({
		closeOnClick: closeOnClick,
		displayCloseButton: displayClose,
		positionClass: position,
		showDuration: duration,
		theme: theme
	})({
		title: title,
		message: message
	});
}

function facialSim(){
    var arr=document.getElementsByTagName('video'); 
    var temp = [].slice.call(arr);
    if(temp.length==0){
        return;
    }
    var video = temp[0];
    if(video.videoHeight!==0){
        var t=video.parentElement.parentElement;
        try {
            var name=t.firstChild.getElementsByClassName('YBp7jf')[0].innerText.replace(/ /g,'_');
            var res=setInterval(async () => {
                const detections = await faceapi.detectAllFaces(video, new faceapi.TinyFaceDetectorOptions()).withFaceLandmarks().withFaceDescriptors()
                if(detections.length!==0){
                    var image = document.createElement("img");
                    image.src = chrome.extension.getURL('images/'+name+".jpg");
                    const result = await faceapi.detectSingleFace(image, new faceapi.TinyFaceDetectorOptions()).withFaceLandmarks().withFaceDescriptor()
                    const dist = faceapi.euclideanDistance(result.descriptor, detections[0].descriptor);
                    if(dist<0.6){
                        clearInterval(res);
                        notify(name," verified",'notification',"nfc-top-left","success");
                    }
                    else{
                        notify(name," not verified",'Warning',"nfc-top-right","error");
                        clearInterval(res);
                    }
                }
                else{
                    console.log(name+' moved out of meeting');
                    clearInterval(res);
                }

            }, 1000)
        }
        catch(err) {
            console.log('type error');
        }
    }
}


function movedOut(){
    var arr=document.getElementsByTagName('video'); 
    var temp = [].slice.call(arr);
    if(temp.length==0){
        return;
    }
    var video = temp[0];
    if(video.videoHeight!==0){
        var t=video.parentElement.parentElement;
        try {
            var name=t.firstChild.getElementsByClassName('YBp7jf')[0].innerText.replace(/ /g,'_');
            const canvas = faceapi.createCanvasFromMedia(video)
            document.body.append(canvas)
            const displaySize = { width: video.parentElement.clientWidth, height: video.parentElement.clientHeight }
            faceapi.matchDimensions(canvas, displaySize)
            setInterval(async () => {
                const detections = await faceapi.detectAllFaces(video, new faceapi.TinyFaceDetectorOptions()).withFaceLandmarks().withFaceDescriptors()
                if(detections.length===0){
                    console.log(name+' moved out of meeting');
                    notify(name," moved out of the view",'Suspicious behavior',"nfc-top-right","error");
                }
            }, 1000);
        }
        catch(err) {
            console.log('type error');
        }
    }
}
facialSim();
movedOut();
