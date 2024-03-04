import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

'Membuka Website Properti BTN'

WebUI.openBrowser('')
WebUI.navigateToUrl(GlobalVariable.UrlHitungProperti)
WebUI.maximizeWindow()
WebUI.takeScreenshot()

'Input Nilai Pemasukan'
if (Penghasilan != '') {
	WebUI.setText(findTestObject('OR_Perhitungan_Properti _BTN/input_Penghasilan'), Penghasilan)
}
WebUI.takeScreenshot()

'Input Nilai Pengeluaran'
if (Pengeluaran != '') {
	WebUI.setText(findTestObject('Object Repository/OR_Perhitungan_Properti _BTN/input_Pengeluaran'),Pengeluaran)
}
WebUI.takeScreenshot()

'Pilih Jangka Waktu'
if (Jangka_Waktu!='') {	
	WebUI.selectOptionByValue(findTestObject('Object Repository/OR_Perhitungan_Properti _BTN/select_jangkaWaktu'), Jangka_Waktu, true)
	WebUI.takeScreenshot()
}

'Verifikasi Pesan error Input Pengeluaran "Isi kurang dari nilai sebelumnya" & Disable tombol hitung, melanjutkan proses hitung harga'
WebUI.scrollToElement(findTestObject("OR_Perhitungan_Properti _BTN/Title_page"),2)
WebUI.verifyTextPresent("Isi kurang dari nilai sebelumnya", false)
WebUI.verifyElementHasAttribute(findTestObject("OR_Perhitungan_Properti _BTN/button_Hitung"), "disabled", 5)
WebUI.takeScreenshot()


WebUI.closeBrowser()



