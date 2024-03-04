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
import io.netty.handler.codec.redis.DefaultBulkStringRedisContent as DefaultBulkStringRedisContent
import org.openqa.selenium.Keys as Keys

'Membuka Website Properti BTN'
WebUI.openBrowser('')

WebUI.navigateToUrl(GlobalVariable.UrlHitungProperti)

WebUI.maximizeWindow()

'Input Nilai Pemasukan'
if (Penghasilan != '') {
    WebUI.setText(findTestObject('OR_Perhitungan_Properti _BTN/input_Penghasilan'), Penghasilan)
}

WebUI.takeScreenshot()

'Input Nilai Pengeluaran'
if (Pengeluaran != '') {
    WebUI.setText(findTestObject('Object Repository/OR_Perhitungan_Properti _BTN/input_Pengeluaran'), Pengeluaran)
}

WebUI.takeScreenshot()

'Pilih Jangka Waktu'
if (Jangka_Waktu != '') {
    WebUI.selectOptionByValue(findTestObject('Object Repository/OR_Perhitungan_Properti _BTN/select_jangkaWaktu'), Jangka_Waktu, 
        true)
}

WebUI.takeScreenshot()

WebUI.click(findTestObject('OR_Perhitungan_Properti _BTN/button_Hitung'))

WebUI.takeScreenshot()

'Vaerifikasi Hasil berasil menampilkan rekomendasi harga yang sesuai dengan perhitungan formula'
WebUI.verifyElementPresent(findTestObject('OR_Perhitungan_Properti _BTN/Title_Harga Properti Maksimal'), 0)

/* validasi hasil perhitungan menggunakan Formula yaitu :
 * (Penghasilan - Pengeluaran) * (Jumlah Bulan * Jangka Waktu) : 3*/
def nilai_Penghasilan = Double.parseDouble(Penghasilan)

def nilai_Pengeluaran = Double.parseDouble(Pengeluaran)

String number_only_JW = Jangka_Waktu.replaceAll("[^0-9]", "")

def angka_JW = Integer.parseInt(number_only_JW)

def nilai_sisa_pendapatan = nilai_Penghasilan - nilai_Pengeluaran

def nilai_Hasil_Jangka_Waktu = 12 * angka_JW

def nilai_perhitungan_properti = (nilai_sisa_pendapatan * nilai_Hasil_Jangka_Waktu) / 3

String Hasil_hitung_rekomen_harga = String.format('%.0f', nilai_perhitungan_properti)

println(Hasil_hitung_rekomen_harga)

def Nilail_Rekomen_hitung_properti = WebUI.getText(findTestObject('OR_Perhitungan_Properti _BTN/Result_Rekomendasi Harga'))

String get_hasil_RekomHarga = Nilail_Rekomen_hitung_properti.replaceAll('[^0-9]', '')
println(get_hasil_RekomHarga)

WebUI.verifyEqual(get_hasil_RekomHarga, Hasil_hitung_rekomen_harga)

WebUI.takeScreenshot()

WebUI.closeBrowser()

