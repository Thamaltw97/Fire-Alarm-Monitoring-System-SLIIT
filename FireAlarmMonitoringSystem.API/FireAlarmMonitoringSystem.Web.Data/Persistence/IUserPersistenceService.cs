using FireAlarmMonitoringSystem.Web.Data.Entities;
using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;

namespace FireAlarmMonitoringSystem.Web.Data.Persistence
{
    public interface IUserPersistenceService
    {
        Task<UserModel> SignIn(UserModel user);
    }
}
