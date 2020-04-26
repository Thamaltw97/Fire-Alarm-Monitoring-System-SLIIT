using FireAlarmMonitoringSystem.Web.Data.Entities;
using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;

namespace FireAlarmMonitoringSystem.Web.Data.Persistence
{
    public interface ISensorPersistenceService
    {
        Task<ApiResult> GetSensors();
        Task<ApiResult> GetSensor(int sensorId);
        Task<ApiResult> AddSensor(SensorModel sensorDetails);
        Task<ApiResult> EditSensor(SensorModel sensorDetails);
        Task<ApiResult> DeleteSensor(int sensorId);
        Task<ApiResult> SetSensorLevel(SensorModel sensorState);
    }
}
