using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Text;

namespace FireAlarmMonitoringSystem.Web.Data.Entities
{
    public class UserModel
    {
        [Key]
        [JsonProperty("userId")]
        public int userId { get; set; }

        [JsonProperty("userEmail")]
        public string userEmail { get; set; }

        [JsonProperty("userName")]
        public string userName { get; set; }

        [JsonProperty("userPassword")]
        public string userPassword { get; set; }

        [JsonProperty("userRole")]
        public string userRole { get; set; }
    }
}
