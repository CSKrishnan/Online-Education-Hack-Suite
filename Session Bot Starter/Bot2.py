from selenium import webdriver
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.options import Options
import time
import re
import os.path
from os import path
import sqlite3
import schedule
from datetime import datetime
from selenium.webdriver.common.action_chains import ActionChains
from datetime import datetime
from selenium.webdriver.chrome.options import Options

chrome_options = Options()
chrome_options.add_argument("--use-fake-ui-for-media-stream")


import mysql.connector

mydb = mysql.connector.connect(
  host="localhost",
  user="root",
  password="bharath7",
  database="tarp"
)
mycursor = mydb.cursor()
mycursor.execute("SELECT * FROM userinfo")
myresult = mycursor.fetchone()

username = myresult[1]
password = myresult[2]
user = myresult[3]
platform = myresult[4]

mycursor.execute("SELECT * FROM timetable")
myresult = mycursor.fetchall()

subject = []
day = []
startHour = []
startMinute = []
endHour = []
endMinute = []

for x in myresult:
      if((datetime.today().isoweekday() % 7) == int(x[1])):
            subject.append(x[0])
            day.append(x[1])
            startHour.append(x[2])
            startMinute.append(x[3])
            endHour.append(x[4])
            endMinute.append(x[5])

for i in range(0,len(startHour)):
      for j in range(i+1, len(startHour)):
            if(int(startHour[i]) > int(startHour[j])):
                  subject[i], subject[j] = subject[j], subject[i]
                  startHour[i], startHour[j] = startHour[j], startHour[i]
                  startMinute[i], startMinute[j] = startMinute[j], startMinute[i]
                  endHour[i], endHour[j] = endHour[j], endHour[i]
                  endMinute[i], endMinute[j] = endMinute[j], endMinute[i]
            elif(int(startHour[i])== int(startHour[j])):
                  if(int(startMinute[i]) > int(startMinute[j])):
                        subject[i], subject[j] = subject[j], subject[i]
                        startHour[i], startHour[j] = startHour[j], startHour[i]
                        startMinute[i], startMinute[j] = startMinute[j], startMinute[i]
                        endHour[i], endHour[j] = endHour[j], endHour[i]
                        endMinute[i], endMinute[j] = endMinute[j], endMinute[i]

print(subject)
print(day)
print(startHour)
print(startMinute)
print(endHour)
print(endMinute)

remainSubject = []
remainDay = []
remainStartHour = []
remainStartMinute = []
remainEndHour = []
remainEndMinute = []

remain = 0
for i in range(0, len(startHour)):
      if(datetime.now().hour < int(startHour[i])):
            remainSubject.append(subject[i])
            remainStartHour.append(startHour[i])
            remainStartMinute.append(startMinute[i])
            remainEndHour.append(endHour[i])
            remainEndMinute.append(endMinute[i])
            print(subject[i])
            print(startHour[i])
            print(startMinute[i])
            print(endHour[i])
            print(endMinute[i])
            remain += 1
      elif(datetime.now().hour == int(startHour[i])):
            if(datetime.now().minute <= int(startMinute[i])):
                  remainSubject.append(subject[i])
                  remainStartHour.append(startHour[i])
                  remainStartMinute.append(startMinute[i])
                  remainEndHour.append(endHour[i])
                  remainEndMinute.append(endMinute[i])
                  print(subject[i])
                  print(startHour[i])
                  print(startMinute[i])
                  print(endHour[i])
                  print(endMinute[i])
                  remain += 1
print(remain)

for i in range(0, len(remainStartHour)):
      print("Next Subject " + remainSubject[i])
      while(int(remainStartHour[i])>datetime.now().hour):
            print("sleep for 5 min")            
            time.sleep(300)
      while(int(remainStartMinute[i])>datetime.now().minute):
            print("sleep for 10 sec")
            time.sleep(10)

      print("Starting " + remainSubject[i])
      driver = webdriver.Chrome("chromedriver.exe", chrome_options=chrome_options)
      if(platform=="MS Teams"):
            driver.get("https://teams.microsoft.com")
            WebDriverWait(driver,10000).until(EC.visibility_of_element_located((By.TAG_NAME,'body')))
            time.sleep(5)
            emailField = driver.find_element_by_xpath('//*[@id="i0116"]')
            emailField.click()
            emailField.send_keys(username)
            driver.find_element_by_xpath('//*[@id="idSIButton9"]').click()
            time.sleep(5)
            passwordField = driver.find_element_by_xpath('//*[@id="i0118"]')
            passwordField.click()
            passwordField.send_keys(password)
            driver.find_element_by_xpath('//*[@id="idSIButton9"]').click()
            time.sleep(5)
            driver.find_element_by_xpath('//*[@id="idSIButton9"]').click()
            time.sleep(25)
            classes_available = driver.find_elements_by_class_name("name-channel-type")
            class_name = remainSubject[i]
            for i in classes_available:
                  if(class_name.lower() in i.get_attribute('innerHTML').lower()):
                        i.click()
                        break
                  print(i.get_attribute('innerHTML').lower())
            time.sleep(5)
            joinbtn = driver.find_element_by_class_name("ts-calling-join-button")
            joinbtn.click()
            time.sleep(4)
            webcam = driver.find_element_by_xpath('//*[@id="page-content-wrapper"]/div[1]/div/calling-pre-join-screen/div/div/div[2]/div[1]/div[2]/div/div/section/div[2]/toggle-button[1]/div/button/span[1]')
            if (webcam.get_attribute('title') == 'Turn camera off'):
                  webcam.click()
            time.sleep(3)
            microphone = driver.find_element_by_xpath('//*[@id="preJoinAudioButton"]/div/button/span[1]')
            if (microphone.get_attribute('title') == 'Mute microphone'):
                  microphone.click()
            time.sleep(3)
            joinnowbtn = driver.find_element_by_xpath(
            '//*[@id="page-content-wrapper"]/div[1]/div/calling-pre-join-screen/div/div/div[2]/div[1]/div[2]/div/div/section/div[1]/div/div/button')
            joinnowbtn.click()
            print(remainEndHour[i])

            time.sleep(5)
            while(int(remainEndHour[i])>datetime.now().hour):
                  print("sleep for 1 min")            
                  time.sleep(60)
            while(int(remainEndMinute[i])>datetime.now().minute):
                  print("sleep for 10 sec")
                  time.sleep(10)
            
            print("Ending " + remainSubject[i])
            driver.find_element_by_id("hangup-button").click()
      else:
            
            driver.get('https://accounts.google.com/ServiceLogin?hl=en&passive=true&continue=https://www.google.com/&ec=GAZAAQ')

            driver.find_element_by_id("identifierId").send_keys(username)
            driver.find_element_by_id("identifierNext").click()
            driver.implicitly_wait(1000)

            driver.find_element_by_xpath('id="password"]/div[1]/div/div[1]/input').send_keys(password)
            driver.implicitly_wait(10)
            driver.find_element_by_id("passwordNext").click()
            driver.implicitly_wait(10)
            driver.get('https://meet.google.com/kis-xiht-ier')

            driver.implicitly_wait(100)
            time.sleep(2)
            driver.find_element_by_xpath(
            '//*[@id="yDmH0d"]/c-wiz/div/div/div[8]/div[3]/div/div/div[2]/div/div[1]/div[1]/div[1]/div/div[4]/div[1]/div/div/div').click()
            driver.implicitly_wait(3000)

            time.sleep(1)
            driver.find_element_by_xpath(
            '//*[@id="yDmH0d"]/c-wiz/div/div/div[8]/div[3]/div/div/div[2]/div/div[1]/div[1]/div[1]/div/div[4]/div[2]/div/div').click()
            driver.implicitly_wait(3000)

            time.sleep(5)
            driver.implicitly_wait(2000)
            driver.find_element_by_css_selector(
            'div.uArJ5e.UQuaGc.Y5sE8d.uyXBBb.xKiqt').click()
