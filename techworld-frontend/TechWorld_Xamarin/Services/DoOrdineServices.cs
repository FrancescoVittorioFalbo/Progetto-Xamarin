using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using TechWorld_Xamarin.Model;

namespace TechWorld_Xamarin.Services
{
    public class DoOrdineServices
    {
        public async Task<Ordine> doOrdine(Ordine now)
        {
            var store = new HttpClient();
            string order = JsonConvert.SerializeObject(now);
            StringContent content = new StringContent(order, Encoding.UTF8, "application/json");

            var response = store.PostAsync("http://192.168.1.98:8080/ordini", content).GetAwaiter().GetResult();
            var orderNow = response.Content.ReadAsStringAsync();
            return JsonConvert.DeserializeObject<Ordine>(orderNow.Result);
        }
    }
}
