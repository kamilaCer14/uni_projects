
from selenium import webdriver
import csv
import time

from selenium.common.exceptions import NoSuchElementException
from selenium.common.exceptions import StaleElementReferenceException
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions

opts = webdriver.ChromeOptions()
opts.headless =True
driver = webdriver.Chrome("/Users/Kamca/Downloads/chromedriver",options=opts)

driver.get("https://www.mall.cz/bezdratove-reproduktory")
time.sleep(10)
speakers = []
speakers_list =[]

count = 0
while True:
         if count==10:
                 break
         count += 1
         print('page',count)
         speakers = driver.find_elements_by_class_name('category-products__item')
         
         for speaker in speakers:
                
                name = speaker.find_elements_by_class_name('product-box-category__title')[0].text
                description = speaker.find_elements_by_class_name('product-box-category__inner-box')[0].text
                price = speaker.find_elements_by_class_name('product-price__wrap')[0].text


                print('title: ',name)
                print('description: ',description)
                print('price: ',price)
                print('_'*100)


                data = [name,'\n' + description,'\n' + price]
                speakers_list.append(data)

              
                with open ('data.csv','w') as file:
                    writer=csv.writer(file)
                    for row in speakers_list:
                     writer.writerow(row)

my_element_id = 'main-content'
ignored_exceptions=(NoSuchElementException,StaleElementReferenceException,)
your_element = WebDriverWait(driver, 10,ignored_exceptions=ignored_exceptions)\
                .until(expected_conditions.presence_of_element_located((By.ID, my_element_id)))
