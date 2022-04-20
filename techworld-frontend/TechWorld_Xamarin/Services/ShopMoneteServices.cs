using Newtonsoft.Json;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using TechWorld_Xamarin.Model;

namespace TechWorld_Xamarin.Services
{
    public class ShopMoneteServices
    {
        public async Task<Cliente> shopCoin(int coin, Cliente utente)
        {
            var client = new HttpClient();
            string userJson = JsonConvert.SerializeObject(utente);
            StringContent content = new StringContent(userJson, Encoding.UTF8, "application/json");

            var response = client.PostAsync("http://192.168.1.98:8080/client/compra/" + utente.username + "/" + utente.password + "/" + coin, content).GetAwaiter().GetResult();
            var user = response.Content.ReadAsStringAsync();
            return JsonConvert.DeserializeObject<Cliente>(user.Result);
        }
    }
}
