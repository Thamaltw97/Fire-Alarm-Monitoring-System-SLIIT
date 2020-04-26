using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Text;

namespace FireAlarmMonitoringSystem.Web.Data.Entities
{
    public class ApiResult
    {
        [JsonProperty("BOOVAL")]
        public bool BOOVAL { get; set; }

        [JsonProperty("MSG")]
        public object MSG { get; set; }
    }
}
