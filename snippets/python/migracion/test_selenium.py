import pytest
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.support.ui import WebDriverWait

from support import fixture_url

# El «antes» de la migración: Selenium WebDriver con pytest.


# #region example
@pytest.fixture
def driver():
    options = webdriver.ChromeOptions()
    options.add_argument("--headless=new")
    driver = webdriver.Chrome(options=options)
    yield driver
    driver.quit()


def test_exportar_pedidos(driver):
    driver.get(fixture_url("pedidos"))
    driver.find_element(By.XPATH, "//button[text()='Cargar pedidos']").click()

    # Exportar se habilita cuando llegan los datos. Un clic sobre un botón deshabilitado no da error: no hace nada.
    wait = WebDriverWait(driver, 5)
    exportar = driver.find_element(By.XPATH, "//button[text()='Exportar']")
    wait.until(EC.element_to_be_clickable(exportar)).click()

    wait.until(EC.text_to_be_present_in_element((By.CSS_SELECTOR, "[role='status']"), "Exportación lista"))
    assert len(driver.find_elements(By.CSS_SELECTOR, "ul[aria-label='Pedidos'] li")) == 3
# #endregion
