using Microsoft.EntityFrameworkCore;
using FireAlarmMonitoringSystem.Web.Data.Entities;
using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;
using System.Linq;
using Microsoft.EntityFrameworkCore.Internal;

namespace FireAlarmMonitoringSystem.Web.Data.Persistence
{
    public class SensorPersistenceService : ISensorPersistenceService
    {
        private readonly AppDbContext _context;

        public SensorPersistenceService() { }
        public SensorPersistenceService(AppDbContext context)
        {
            _context = context;
        }

        public async Task<ApiResult> DeleteSensor(int id)
        {
            SensorModel dbModelObj = await _context.SensorDetails.FindAsync(id);
            if (dbModelObj == null)
            {
                return new ApiResult { BOOVAL = false, MSG = "No Sensor Found !" };
            }
            else
            {
                _context.SensorDetails.Remove(dbModelObj);
                await _context.SaveChangesAsync();
                return new ApiResult { BOOVAL = true, MSG = "Sucessfully Deleted." };
            }
            
        }

        public async Task<ApiResult> EditSensor(SensorModel modelObj)
        {
            SensorModel dbModelObj = await _context.SensorDetails.FindAsync(modelObj.sensorId);
            if (modelObj == null)
            {
                return new ApiResult { BOOVAL = false, MSG = "Please enter correct sensor details !" };
            }
            else if (dbModelObj == null)
            {
                return new ApiResult { BOOVAL = false, MSG = "No Sensor Found !" };
            }
            else
            {
                dbModelObj.sensorName = modelObj.sensorName;
                dbModelObj.floorNo = modelObj.floorNo;
                dbModelObj.roomNo = modelObj.roomNo;
                dbModelObj.sensorStatus = modelObj.sensorStatus;

                await _context.SaveChangesAsync();
                return new ApiResult { BOOVAL = true, MSG = "Sucessfully Updated." };
            }

        }

        public async Task<ApiResult> GetSensors()
        {
            var retSensorObj = await _context.SensorDetails.Where(sensorDetails => sensorDetails.sensorStatus.Equals("A")).ToListAsync();
            return new ApiResult { BOOVAL = true, MSG = retSensorObj };
        }

        public async Task<ApiResult> GetSensor(int id)
        {
            SensorModel dbModelObj = await _context.SensorDetails.FindAsync(id);
            if (dbModelObj == null)
            {
                return new ApiResult { BOOVAL = false, MSG = "No Sensor Found !" };
            }
            else
            {
                var sensor = await _context.SensorDetails.Where(sensorDetails => sensorDetails.sensorId.Equals(id)).ToListAsync();
                return new ApiResult { BOOVAL = true, MSG = sensor };
            }
            
        }

        public async Task<ApiResult> AddSensor(SensorModel sensorDetObj)
        {
            if (sensorDetObj != null)
            {
                await _context.AddAsync(sensorDetObj);
                try
                {
                    await _context.SaveChangesAsync();
                    return new ApiResult { BOOVAL = true, MSG = "Sucessfully Saved." };
                }
                catch (DbUpdateException dbUpdateEx)
                {
                    return new ApiResult { BOOVAL = false, MSG = "Error saving data - " + dbUpdateEx };
                }
                catch (Exception ex)
                {
                    return new ApiResult { BOOVAL = false, MSG = "Error saving data - " + ex };
                }
            }
            else
            {
                return new ApiResult { BOOVAL = false, MSG = "Please enter correct sensor details !" };
            }
        }

        public async Task<ApiResult> SetSensorLevel(SensorModel sensorState)
        {
            if (sensorState != null)
            {
                SensorModel dbSensorObj = await _context.SensorDetails.FindAsync(sensorState.sensorId);
                if (dbSensorObj == null || sensorState == null)
                    return new ApiResult { BOOVAL = false, MSG = "No Sensor Found !" };

                dbSensorObj.smokeLevel = sensorState.smokeLevel;
                dbSensorObj.coLevel = sensorState.coLevel;

                await _context.SaveChangesAsync();
                return new ApiResult { BOOVAL = true, MSG = "Sucessfully Updated." };
            }
            else
            {
                return new ApiResult { BOOVAL = false, MSG = "Please enter correct sensor details !" };
            }
        }
    }
}
