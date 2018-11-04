package irul.com.footballmatchschedule.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.squareup.picasso.Picasso
import irul.com.footballmatchschedule.R
import irul.com.footballmatchschedule.model.PlayerDetail
import kotlinx.android.synthetic.main.activity_detail_player.*

class DetailPlayerActivity : AppCompatActivity() {
    lateinit var playerDetail:PlayerDetail
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_player)
        playerDetail = intent.getParcelableExtra("detailPlayer")
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title =playerDetail.strPlayer
        Picasso.get().load(playerDetail.strFanart1).into(image)
        weight.text = playerDetail.strWeight
        height.text = playerDetail.strHeight
        position.text = playerDetail.strPosition
        description.text = playerDetail.strDescriptionEN
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}