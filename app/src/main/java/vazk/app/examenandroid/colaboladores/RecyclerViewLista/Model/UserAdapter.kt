import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_user_layout.view.*
import vazk.app.examenandroid.R
import vazk.app.examplejson.Model.UserModelClass


  class UserAdapter(val context: Context, val items: ArrayList<UserModelClass>,

        ) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_user_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items.get(position)
        holder.tvId.text = item.id
        holder.tvName.text = item.name
        holder.tvEmail.text = item.mail
        holder.tvMobileNumber.text = item.location.lat
        holder.tvOfficeNumber.text = item.location.log


    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvId = view.tv_id
        val tvName = view.tv_name
        val tvEmail = view.tv_email
        val tvMobileNumber = view.tv_mobile
        val tvOfficeNumber = view.tv_office_number
    }
}