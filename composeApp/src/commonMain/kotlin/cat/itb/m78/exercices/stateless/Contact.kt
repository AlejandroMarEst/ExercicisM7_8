package cat.itb.m78.exercices.stateless

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import m78exercices.composeapp.generated.resources.Res
import m78exercices.composeapp.generated.resources.generatedFace
import org.jetbrains.compose.resources.painterResource

@Composable
fun Contact(){
    data class Contact(val fullName: String, val email: String, val phone: String)
    val contact = Contact("Marta Casserres", "marta@example.com", "934578484")
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        Image(
            painter = painterResource(Res.drawable.generatedFace),
            modifier = Modifier.size(100.dp).clip(CircleShape),
            contentDescription = null
        )
        Text(contact.fullName, fontSize = 2.em, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.size(25.dp))
        Card() {
            Row(modifier=Modifier.padding(10.dp)){
                Icon(Icons.Default.Email, "")
                Text(contact.email)
            }
            Row(modifier=Modifier.padding(15.dp)){
                Icon(Icons.Default.Phone, "")
                Text(contact.phone)
            }
        }
    }
}

