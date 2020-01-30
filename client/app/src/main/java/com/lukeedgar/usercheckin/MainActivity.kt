package com.lukeedgar.usercheckin

import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.MifareClassic
import android.nfc.tech.MifareUltralight
import android.nfc.tech.Ndef
import android.nfc.tech.NfcA
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lukeedgar.usercheckin.nfc.NfcComms
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var nfcAdapter: NfcAdapter
    private lateinit var pendingIntent: PendingIntent
    private lateinit var intentFiltersArray: Array<IntentFilter>
    private lateinit var techListsArray: Array<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nfcAdapter = NfcAdapter.getDefaultAdapter(this)

        val intent = Intent(this, javaClass).apply {
            addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        }
        pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val ndef = IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED).apply {
            try {
                addDataType("*/*")    /* Handles all MIME based dispatches.
                             You should specify only the ones that you need. */
            } catch (e: IntentFilter.MalformedMimeTypeException) {
                throw RuntimeException("fail", e)
            }
        }

        intentFiltersArray = arrayOf(ndef)

        techListsArray = arrayOf(
            arrayOf(
                MifareUltralight::class.java.name,
                Ndef::class.java.name,
                NfcA::class.java.name
            ), arrayOf(
                MifareClassic::class.java.name,
                Ndef::class.java.name,
                NfcA::class.java.name
            )
        )

        email_button.setOnClickListener {
            val emailSender = EmailSender(applicationContext)
            val content = "1"
            val email = Email("lukeedgar@outlook.com","lukeedgar@outlook.com", content)
            emailSender.send(email)
            Toast.makeText(applicationContext, "Thanks! An email has been sent", Toast.LENGTH_LONG).show()
            instruction_textview.text = "Email sent!"
        }


        //val tagFromIntent: MifareUltralight = MifareUltralight.get(intent.getParcelableExtra(NfcAdapter.EXTRA_TAG))
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        val tagFromIntent: Tag? = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG)
        nfcHandler(tagFromIntent!!)
    }

    public override fun onPause() {
        super.onPause()
        nfcAdapter.disableForegroundDispatch(this)
    }

    public override fun onResume() {
        super.onResume()
        nfcAdapter.enableForegroundDispatch(this, pendingIntent, null, null)
    }

    private fun nfcHandler(tag : Tag) {
        val nfcComms = NfcComms()
        Toast.makeText(applicationContext, "gfdgdfgdfgdg", Toast.LENGTH_LONG).show()
        if (rw_switch.isChecked){
            instruction_textview.text = getString(R.string.writing_nfc)
            nfcComms.writeTag(tag, "bears")
            instruction_textview.text = getString(R.string.write_nfc_success)
        }else {
            instruction_textview.text = getString(R.string.reading_nfc)
            val content = nfcComms.readTag(tag)
            Toast.makeText(applicationContext, content, Toast.LENGTH_LONG ).show()
            instruction_textview.text = getString(R.string.checkin_success)
        }
    }
}
