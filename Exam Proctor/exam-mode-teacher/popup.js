var imageName,imageURL,studentName;
        var files=[];
        var reader;
        const firebaseConfig = {
            apiKey: "AIzaSyB0H7vZz1JgR5T8TrVPyATjcc-SEU2LWzI",
            authDomain: "exam-mode.firebaseapp.com",
            databaseURL: "https://exam-mode-default-rtdb.firebaseio.com",
            projectId: "exam-mode",
            storageBucket: "exam-mode.appspot.com",
            messagingSenderId: "394605925099",
            appId: "1:394605925099:web:63544e57a229427f4624ae"
        };
        firebase.initializeApp(firebaseConfig);
        document.getElementById('image').onclick=function(e){
            var input=document.createElement('input');
            input.type='file';

            input.onchange=e=>{
                files=e.target.files;
                reader=new FileReader();
                reader.onload=function(){
                    document.getElementById("imagePreview").src=reader.result;
                }
                reader.readAsDataURL(files[0]);
            }
            input.click();
        }
        document.getElementById('upload').onclick=function(){
            imageName=document.getElementById('regno').value;
            studentName=document.getElementById('name').value;

            var uploadTask=firebase.storage().ref('Images/'+imageName+".png").put(files[0]);
            uploadTask.on('state_changed',function(snapshot){
                var progress=(snapshot.bytesTransferred/snapshot.totalBytes)*100;
            },
            function(error){
                alert('error in saving the image');
            },
            function(){
                uploadTask.snapshot.ref.getDownloadURL().then(function(url){
                    imageURL=url;                
                    firebase.database().ref('Data/'+imageName).set({
                        Name:studentName,
                        RegNo:imageName,
                        Link:imageURL,
                    });
                    alert('data added successfully');
                    document.getElementById("registerForm").reset();
                    document.getElementById('imagePreview').src='https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSG2h3dtkFclxksGm2bXE8R53sUemVyVGmJTg&usqp=CAU';
                }
                );
            });
        }
        