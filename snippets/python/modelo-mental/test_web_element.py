import pytest
from selenium import webdriver
from selenium.common.exceptions import StaleElementReferenceException
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait

from support import fixture_url


@pytest.fixture
def driver():
    options = webdriver.ChromeOptions()
    options.add_argument("--headless=new")
    driver = webdriver.Chrome(options=options)
    driver.get(fixture_url("pedidos"))
    yield driver
    driver.quit()


# #region example
def test_web_element_queda_obsoleto(driver):
    wait = WebDriverWait(driver, 5)
    cargar = driver.find_element(By.XPATH, "//button[text()='Cargar pedidos']")
    estado = driver.find_element(By.CSS_SELECTOR, "[role='status']")
    cargar.click()
    wait.until(lambda _: estado.text == "3 pedidos")

    primero = driver.find_element(By.CSS_SELECTOR, "ul[aria-label='Pedidos'] li")
    assert primero.text == "PED-1001 · Pagado"

    # La página reemplaza la lista: el elemento que se guardó ya no existe en el DOM.
    cargar.click()
    wait.until(lambda _: estado.text == "3 pedidos")
    with pytest.raises(StaleElementReferenceException):  # [!mark]
        primero.text
# #endregion
