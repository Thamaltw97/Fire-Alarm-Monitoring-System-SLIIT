using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using FireAlarmMonitoringSystem.Web.Data.Entities;
using FireAlarmMonitoringSystem.Web.Data.Persistence;
using Microsoft.AspNetCore.Authorization;

namespace FireAlarmMonitoringSystem.Web.API.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class SensorController : ControllerBase
    {
        private readonly AppDbContext _context;
        private readonly ISensorPersistenceService _sensorPerService;

        public SensorController(AppDbContext context, ISensorPersistenceService sensorPerService)
        {
            _context = context;
            _sensorPerService = sensorPerService;
        }

        [HttpGet("GetSensors")]
        public async Task<ApiResult> GetSensors()
        {
            return await _sensorPerService.GetSensors();
        }

        [HttpGet("GetSensor/{id}")]
        public async Task<ApiResult> GetSensor(int id)
        {
            return await _sensorPerService.GetSensor(id);
        }

        [HttpPost("AddSensor")]
        public async Task<ApiResult> AddSensor(SensorModel sensorDetObj)
        {
            return await _sensorPerService.AddSensor(sensorDetObj);
        }

        [HttpPut("EditSensor")]
        public async Task<ApiResult> EditSensor(SensorModel sensorDetObj)
        {
            return await _sensorPerService.EditSensor(sensorDetObj);
        }

        [HttpDelete("DeleteSensor/{id}")]
        public async Task<ApiResult> DeleteSensor(int id)
        {
            return await _sensorPerService.DeleteSensor(id);
        }

        [HttpPost("SetSensorLevel")]
        public async Task<ApiResult> SetSensorLevel(SensorModel sensorDetObj)
        {
            return await _sensorPerService.SetSensorLevel(sensorDetObj);
        }
    }
}