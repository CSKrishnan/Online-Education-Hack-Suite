# Online Education Hack Suite
The main objective of this project is to add features to the online meeting platforms like Microsoft Teams and Google meet to help the teachers for effective teaching in online classes and to proctor the students during the remote examinations. 

### Block diagram

### Requirements
* face-api.js -  It is a deep learning library which is used to perform facial  detection and  recognition. The model ResNet-34 like architecture with which it computes feature vectors of size 128 called as face descriptor. 
* firebase realtime database -  It is a NoSQL database hosted on cloud which can help us to store and retrieve data in realtime. It is used to fetch the notifications from students.
* firebase storage -  It is used to store the student’s latest image and their academic details.
* Java 1.8
* Python and the following Pip packages :
  * OpenCV - It is used in the Whiteboard feature to  apply binary mask and contour detection for tracking location of particular color.
  * Schedule - It is a lightweight package for job scheduling 
  * SQL database connector


### To run:
1.  Whiteboard
    * go to the Whiteboard folder
    * install opencv -  `pip install opencv-python`
    * execute `python board.py` in the command prompt
2.  Exam Proctor
    * for proctor 
      * Open chrome extensions. (chrome://extensions)
      * Turn on developer mode
      * Press load unpacked and select exam-mode-teacher subfolder
    * for students
      * Open chrome extensions. (chrome://extensions)
      * Turn on developer mode
      * Press load unpacked and select exam mode subfolder
3.  Session bot starter
    * install java 1.8
    * install pip packages
      `pip install schedule`
    * execute `python bot2.py`
    * clone the Session bot starter subfolder and run the application.
  
### Sample screenshot
 
### References
1.  Rishabh Karmakar. “Facial Attendance System Using MTCNN And Feature Mapping”, in International Journal of Engineering Applied Sciences and Technology,Vol. 5, Issue 4, 2020.
2.   Istiak Ahmad, Fahad AlQurashi, Ehab Abozinadah, Rashid Mehmood. “A Novel Deep Learning-based Online Proctoring System using Face Recognition, Eye Blinking, and Object Detection Techniques'', in (IJACSA) International Journal of Advanced Computer Science and Applications, Vol. 12, No. 10, 2021.
3.  Xiaochao Li, , Zhenjie Yang , And Hongwei Wu. “Face Detection Based on Receptive Field Enhanced Multi-Task Cascaded Convolutional Neural Networks”, in IEEE, Volume: 8, 2020.

