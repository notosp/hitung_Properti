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
import java.text.NumberFormat as NumberFormat

WebUI.openBrowser('')

WebUI.navigateToUrl(GlobalVariable.UrlHitungProperti)

WebUI.maximizeWindow()

if (WebUI.verifyElementNotClickable(findTestObject('OR_Perhitungan_Properti _BTN/button_Hitung'))) {
    if (Penghasilan != '') {
        WebUI.setText(findTestObject('OR_Perhitungan_Properti _BTN/input_Penghasilan'), Penghasilan)
    }
    
    if (Pengeluaran != '') {
        WebUI.setText(findTestObject('Object Repository/OR_Perhitungan_Properti _BTN/input_Pengeluaran'), 
            Pengeluaran)
    } else if (Pengeluaran > Penghasilan) {
        WebUI.verifyElementText(findTestObject('OR_Perhitungan_Properti _BTN/WarningMsg'), ExpecWarningMessage)
    }
    
    if (jangkaWaktu != '') {
        WebUI.selectOptionByValue(findTestObject('Object Repository/OR_Perhitungan_Properti _BTN/select_jangkaWaktu'), 
            jangkaWaktu, true)
    } else {
        WebUI.click(findTestObject('OR_Perhitungan_Properti _BTN/button_Hitung'))
    }
}

if (WebUI.verifyElementClickable(findTestObject('OR_Perhitungan_Properti _BTN/button_Hitung'))) {
    WebUI.click(findTestObject('OR_Perhitungan_Properti _BTN/button_Hitung'))
}

//=============================
NumberFormat format = NumberFormat.getInstance(Locale.US)

String a = Penghasilan

String b = Pengeluaran

String c = jangkaWaktu

number_a = format.parse(a)

number_b = format.parse(b)

number_c = format.parse(c)

Rumus = ((((number_a - number_b) * number_c) * 12) / 3)

cekDataHitung = WebUI.getText(findTestObject('OR_Perhitungan_Properti _BTN/HasilHitung'))

dataHitungNew = cekDataHitung.replace('Rp ', '').replace('.', '').toString()

//formatPerhitungan = Rumus.toString()
println('Hasil Rumus = ' + Rumus)
println('Hasil Web = ' + dataHitungNew)

if (dataHitungNew == Rumus) {
	WebUI.waitForElementVisible(findTestObject('OR_Perhitungan_Properti _BTN/HasilHitung'), 0)
	WebUI.comment('Valid')
} else if (dataHitungNew != Rumus) {
	WebUI.comment('Not Valid')
}


WebUI.closeBrowser()

